package com.example.weeklymeals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.weeklymeals.ui.navigation.Navigation
import com.example.weeklymeals.ui.screens.HomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeeklyMealsApp {
                Navigation()
            }
        }
    }
}

/*@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview
@Composable
fun Preview(){
    WeeklyMealsApp {
        HomeScreen()
    }
}*/