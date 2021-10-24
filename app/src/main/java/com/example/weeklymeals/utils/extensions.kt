package com.example.weeklymeals.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.example.weeklymeals.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Meal.getMealTypeText(): String {
    return if(mealType == LUNCH) stringResource(id = R.string.lunch) else stringResource(id = R.string.diner)
}
