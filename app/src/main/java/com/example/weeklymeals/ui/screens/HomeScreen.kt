package com.example.weeklymeals.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.example.weeklymeals.R
import com.example.weeklymeals.ui.composables.FabShare
import com.example.weeklymeals.ui.composables.MainAppBar
import com.example.weeklymeals.ui.composables.MyPager
import com.example.weeklymeals.ui.composables.RowDayClickable
import com.example.weeklymeals.ui.initialState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(getDailyMeals: GetDailyMeals){

    val context= LocalContext.current
    var myWeek by rememberSaveable { mutableStateOf(initialState(context)) }
    var titleDay by rememberSaveable { mutableStateOf(myWeek[0].day) }

    /** TODO ADD USE CASE LOGIC */

    Scaffold(
        topBar = { MainAppBar(title = titleDay) }
    ) { paddingValues ->

        HomeContent(
            paddingValues = paddingValues,
            myWeek = myWeek,
            titleDay = titleDay,
            onTitleChange = {
                titleDay = it
            }
        )

    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeContent(paddingValues: PaddingValues, myWeek: List<Day>, titleDay: String, onTitleChange: (String) -> Unit){

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var idPressed by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.BottomStart
    ){

        val stateId = pagerState.currentPage
        if (stateId != idPressed) {
            idPressed = stateId
            onTitleChange(myWeek[stateId].day)
        }

        MyPager(
            week = myWeek,
            pagerState = pagerState
        )

        RowDayClickable(
            days = myWeek,
            id = idPressed,
            onClick = { day ->
                coroutineScope.launch{
                    day.id.let {
                        pagerState.animateScrollToPage(it)
                    }
                }
            }
        )
    }
}