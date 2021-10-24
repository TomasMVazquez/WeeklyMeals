package com.example.weeklymeals

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.weeklymeals.ui.theme.WeeklyMealsTheme

@Composable
fun WeeklyMealsApp(content: @Composable () -> Unit){
    WeeklyMealsTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}