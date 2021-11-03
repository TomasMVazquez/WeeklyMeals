package com.example.weeklymeals.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.example.weeklymeals.R
import com.example.weeklymeals.ui.composables.BackTopAppBar
import com.example.weeklymeals.ui.composables.EditableDayItem
import com.example.weeklymeals.utils.initialState
import org.koin.androidx.compose.getViewModel

@Composable
fun EditScreen(myWeek: List<Day>, onBackClick: () -> Unit, editViewModel: EditViewModel = getViewModel()){

    val weekMeals by editViewModel.weekMeals.observeAsState(initial = initialState())
    editViewModel.starting(myWeek) //TODO CHANGE BY LIST FROM HOME

    val savingState by editViewModel.saving.observeAsState(initial = false)

    Scaffold(
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.edit_title),
                onBackClick = { if (!savingState) onBackClick() }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.save))
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Save,
                        contentDescription = stringResource(R.string.description_icon_save)
                    )
                },
                backgroundColor = if (!savingState) MaterialTheme.colors.secondary else Color.Gray,
                onClick = {
                    if (!savingState) editViewModel.saveListToDB(weekMeals)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ){ paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(weekMeals){ day ->
                EditableDayItem(day = day)
            }
        }
    }
}
