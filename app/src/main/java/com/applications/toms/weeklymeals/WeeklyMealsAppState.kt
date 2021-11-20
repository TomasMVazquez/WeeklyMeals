package com.applications.toms.weeklymeals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun rememberWeeklyMealsAppState(
    navController: NavHostController = rememberNavController(),
    systemUiController: SystemUiController = rememberSystemUiController()
): WeeklyMealsAppState = remember(navController,systemUiController) {
    WeeklyMealsAppState(navController,systemUiController)
}

class WeeklyMealsAppState(
    val nacController: NavHostController,
    val systemUiController: SystemUiController
)