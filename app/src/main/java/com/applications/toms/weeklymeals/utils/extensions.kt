package com.applications.toms.weeklymeals.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.applications.toms.weeklymeals.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

@Composable
fun Meal.getMealTypeText(): String {
    return if(mealType == LUNCH) stringResource(id = R.string.lunch) else stringResource(id = R.string.diner)
}

@Composable
fun List<Day>.asDeeplinkString(): String {
    var query = ""
    this.forEach {
        query +="${it.day}=${it.lunch.getMealTypeText()}:${it.lunch.meal.replace(" ","_")}" +
                "|${it.diner.getMealTypeText()}:${it.diner.meal.replace(" ","_")}"
        if (this.last() != it) query += "-"
    }
    return query
}

@Composable
fun String.fromDeepLink(): List<Day> {
    val days: List<String> = this.split("-")
    val shareWeek = mutableListOf<Day>()
    for (index in 0..6){
        val stringDay = days[index]
        val day = Day(
            id = index,
            day = stringDay.split("=").first(),
            lunch = Meal(
                mealType = LUNCH,
                meal = stringDay.split("=").last().split("|").first().split(":").last().replace("_"," ")
            ),
            diner = Meal(
                mealType = DINER,
                meal = stringDay.split("=").last().split("|").last().split(":").last().replace("_"," ")
            )
        )
        shareWeek.add(day)
    }
    /*days.forEach {
        val day = Day(
            id = days.indexOf(it),
            day = it.split("=").first(),
            lunch = Meal(
                mealType = LUNCH,
                meal = it.split("=").last().split("|").first().split(":").last().replace("_"," ")
            ),
            diner = Meal(
                mealType = DINER,
                meal = it.split("=").last().split("|").last().split(":").last().replace("_"," ")
            )
        )
        shareWeek.add(day)
    }*/
    return shareWeek.toList()
}

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson<T>(json, object : TypeToken<T>() {}.type)