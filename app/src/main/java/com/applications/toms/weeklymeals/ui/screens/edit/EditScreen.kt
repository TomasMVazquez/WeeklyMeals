package com.applications.toms.weeklymeals.ui.screens.edit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.composables.BackTopAppBar
import com.applications.toms.weeklymeals.ui.composables.DefaultSnackbar
import com.applications.toms.weeklymeals.ui.composables.EditableDayItem
import com.applications.toms.weeklymeals.ui.composables.SaveFloatingActionButton
import com.applications.toms.weeklymeals.ui.composables.SnackBarType
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@Composable
fun EditScreen(
    shareList: List<Day> = emptyList(),
    onBackClick: () -> Unit,
    editViewModel: EditViewModel = getViewModel()
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val state by editViewModel.state.collectAsState()

    LaunchedEffect(key1 = editViewModel.effect) {
        editViewModel.effect.collect { effect ->
            when (effect) {
                is EditViewModel.Effect.Error -> {
                    editViewModel.addSnackBarType(SnackBarType.ERROR)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = when (effect.error) {
                            ErrorStates.EMPTY,
                            ErrorStates.NOT_FOUND,
                            ErrorStates.NOT_SAVED,
                            ErrorStates.GENERIC,
                            ErrorStates.THROWABLE,
                            ErrorStates.NONE,
                            ErrorStates.EMPTY_LIST,
                            ErrorStates.DB_ERROR -> "Oops! Hubo un error, intentalo más tarde"
                            null -> "Oops! Hubo un error, intentalo más tarde"
                        }
                    )
                }
                EditViewModel.Effect.Saved -> {
                    editViewModel.addSnackBarType(SnackBarType.SUCCESS)
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = "¡Lista guardada!"
                    )
                }
            }
        }
    }

    if (shareList.isNotEmpty())
        editViewModel.getShareList(shareList)

    Scaffold(
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.edit_title),
                onBackClick = { if (!state.ready) onBackClick() }
            )
        },
        floatingActionButton = {
            if (state.ready)
                SaveFloatingActionButton {
                    editViewModel.saveListToDB(shareList.ifEmpty { state.week })
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
        scaffoldState = scaffoldState,
        snackbarHost = { scaffoldState.snackbarHostState }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(state.week) { day ->
                    EditableDayItem(
                        day = day,
                        onDayChange = {
                            editViewModel.onEditDay(it)
                        }
                    )
                }
            }

            DefaultSnackbar(
                snackbarHostState = scaffoldState.snackbarHostState,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
                modifier = Modifier.align(Alignment.BottomCenter),
                snackBarType = state.snackBarType
            )
        }
    }
}
