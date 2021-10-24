package com.example.weeklymeals.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.example.weeklymeals.R

@Composable
fun Meal.getMealTypeText(): String {
    return if(mealType == LUNCH) stringResource(id = R.string.lunch) else stringResource(id = R.string.diner)
}
