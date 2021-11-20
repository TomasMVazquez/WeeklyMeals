package com.applications.toms.weeklymeals.ui.screens

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
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.composables.BackTopAppBar
import com.applications.toms.weeklymeals.ui.composables.EditableDayItem
import com.applications.toms.weeklymeals.ui.composables.SaveFloatingActionButton
import com.applications.toms.weeklymeals.utils.initialState
import org.koin.androidx.compose.getViewModel

@Composable
fun EditScreen(myWeek: List<Day>, onBackClick: () -> Unit, editViewModel: EditViewModel = getViewModel()){

    val weekMeals by editViewModel.weekMeals.observeAsState(initial = initialState())
    if (myWeek.isNotEmpty()) editViewModel.starting(myWeek) //TODO CHANGE BY LIST FROM HOME

    val savingState by editViewModel.saving.observeAsState(initial = false)

    Scaffold(
        topBar = {
            BackTopAppBar(
                title = stringResource(R.string.edit_title),
                onBackClick = { if (!savingState) onBackClick() }
            )
        },
        floatingActionButton = {
            SaveFloatingActionButton(enable = savingState) {
                editViewModel.saveListToDB(weekMeals)
            }
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
