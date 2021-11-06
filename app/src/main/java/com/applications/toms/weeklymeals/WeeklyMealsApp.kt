package com.applications.toms.weeklymeals

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.applications.toms.weeklymeals.ui.theme.WeeklyMealsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun WeeklyMealsApp(content: @Composable () -> Unit){
    WeeklyMealsTheme {
        val systemUiController = rememberSystemUiController()
        val statusBarColor = MaterialTheme.colors.primaryVariant
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = statusBarColor,
                darkIcons = false
            )
        }
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}