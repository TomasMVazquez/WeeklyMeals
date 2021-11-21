package com.applications.toms.weeklymeals

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.applications.toms.weeklymeals.ui.navigation.Navigation
import com.applications.toms.weeklymeals.ui.theme.WeeklyMealsTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun WeeklyMealsApp(){
    val appState = rememberWeeklyMealsAppState()

    WeeklyMealsTheme {
        val statusBarColor = MaterialTheme.colors.primaryVariant
        SideEffect {
            appState.systemUiController.setSystemBarsColor(
                color = statusBarColor,
                darkIcons = false
            )
        }
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Navigation(navController = appState.nacController)
        }
    }
}