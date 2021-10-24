package com.example.weeklymeals.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weeklymeals.WeeklyMealsApp
import com.example.weeklymeals.ui.screens.EditScreen
import com.example.weeklymeals.ui.screens.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.get

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home"){
            WeeklyMealsApp {
                HomeScreen(getDailyMeals = get())
            }
        }
        composable("edit"){
            EditScreen()
        }
    }
}