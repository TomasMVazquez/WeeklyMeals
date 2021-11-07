package com.applications.toms.weeklymeals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import com.applications.toms.weeklymeals.ui.navigation.Navigation
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