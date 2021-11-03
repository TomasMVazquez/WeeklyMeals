package com.example.weeklymeals.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.example.weeklymeals.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun Meal.getMealTypeText(): String {
    return if(mealType == LUNCH) stringResource(id = R.string.lunch) else stringResource(id = R.string.diner)
}

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)