package com.example.weeklymeals.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.example.weeklymeals.R

@Composable
fun initialState(): List<Day> {
    val day0 = stringResource(id = R.string.monday)
    val day1 = stringResource(id = R.string.tuesday)
    val day2 = stringResource(id = R.string.wednesday)
    val day3 = stringResource(id = R.string.thursday)
    val day4 = stringResource(id = R.string.friday)
    val day5 = stringResource(id = R.string.saturday)
    val day6 = stringResource(id = R.string.sunday)

    val lunchOf = stringResource(id = R.string.lunch_of)
    val dinerOf = stringResource(id = R.string.diner_of)

   return listOf<Day>(
        Day(0, day0, Meal(LUNCH,"$lunchOf $day0"), Meal(DINER,"$dinerOf $day0")),
        Day(1, day1, Meal(LUNCH,"$lunchOf $day1"), Meal(DINER,"$dinerOf $day1")),
        Day(2, day2, Meal(LUNCH,"$lunchOf $day2"), Meal(DINER,"$dinerOf $day2")),
        Day(3, day3, Meal(LUNCH,"$lunchOf $day3"), Meal(DINER,"$dinerOf $day3")),
        Day(4, day4, Meal(LUNCH,"$lunchOf $day4"), Meal(DINER,"$dinerOf $day4")),
        Day(5, day5, Meal(LUNCH,"$lunchOf $day5"), Meal(DINER,"$dinerOf $day5")),
        Day(6, day6, Meal(LUNCH,"$lunchOf $day6"), Meal(DINER,"$dinerOf $day6")),
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

    val lunchOf = context.resources.getString(R.string.lunch_of)
    val dinerOf = context.resources.getString(R.string.diner_of)

    return listOf<Day>(
        Day(0, day0, Meal(LUNCH,"$lunchOf $day0"), Meal(DINER,"$dinerOf $day0")),
        Day(1, day1, Meal(LUNCH,"$lunchOf $day1"), Meal(DINER,"$dinerOf $day1")),
        Day(2, day2, Meal(LUNCH,"$lunchOf $day2"), Meal(DINER,"$dinerOf $day2")),
        Day(3, day3, Meal(LUNCH,"$lunchOf $day3"), Meal(DINER,"$dinerOf $day3")),
        Day(4, day4, Meal(LUNCH,"$lunchOf $day4"), Meal(DINER,"$dinerOf $day4")),
        Day(5, day5, Meal(LUNCH,"$lunchOf $day5"), Meal(DINER,"$dinerOf $day5")),
        Day(6, day6, Meal(LUNCH,"$lunchOf $day6"), Meal(DINER,"$dinerOf $day6")),
    )
}