package com.applications.toms.weeklymeals.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.applications.toms.weeklymeals.R

@Composable
fun initialState(): List<Day> {
    val day0 = stringResource(id = R.string.monday)
    val day1 = stringResource(id = R.string.tuesday)
    val day2 = stringResource(id = R.string.wednesday)
    val day3 = stringResource(id = R.string.thursday)
    val day4 = stringResource(id = R.string.friday)
    val day5 = stringResource(id = R.string.saturday)
    val day6 = stringResource(id = R.string.sunday)

   return listOf<Day>(
        Day(0, day0, Meal(LUNCH,""), Meal(DINER,"")),
        Day(1, day1, Meal(LUNCH,""), Meal(DINER,"")),
        Day(2, day2, Meal(LUNCH,""), Meal(DINER,"")),
        Day(3, day3, Meal(LUNCH,""), Meal(DINER,"")),
        Day(4, day4, Meal(LUNCH,""), Meal(DINER,"")),
        Day(5, day5, Meal(LUNCH,""), Meal(DINER,"")),
        Day(6, day6, Meal(LUNCH,""), Meal(DINER,"")),
    )
}

fun initialState(context: Context): List<Day> {
    val day0 = context.resources.getString(R.string.monday)
    val day1 = context.resources.getString(R.string.tuesday)
    val day2 = context.resources.getString(R.string.wednesday)
    val day3 = context.resources.getString(R.string.thursday)
    val day4 = context.resources.getString(R.string.friday)
    val day5 = context.resources.getString(R.string.saturday)
    val day6 = context.resources.getString(R.string.sunday)

    return listOf<Day>(
        Day(0, day0, Meal(LUNCH,""), Meal(DINER,"")),
        Day(1, day1, Meal(LUNCH,""), Meal(DINER,"")),
        Day(2, day2, Meal(LUNCH,""), Meal(DINER,"")),
        Day(3, day3, Meal(LUNCH,""), Meal(DINER,"")),
        Day(4, day4, Meal(LUNCH,""), Meal(DINER,"")),
        Day(5, day5, Meal(LUNCH,""), Meal(DINER,"")),
        Day(6, day6, Meal(LUNCH,""), Meal(DINER,"")),
    )
}