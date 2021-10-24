package com.example.weeklymeals.data

import com.applications.toms.domain.Day
import com.applications.toms.domain.Meal
import com.applications.toms.domain.Meal.MealType.*
import com.example.weeklymeals.data.database.DailyMeal

fun Day.toDatabaseModel(): DailyMeal {
    return DailyMeal(
        id = id,
        day = day,
        lunch = lunch.meal,
        diner = diner.meal
    )
}

fun DailyMeal.toDomainModel(): Day {
    return Day(
        id = id,
        day = day,
        lunch = Meal(LUNCH, lunch),
        diner = Meal(DINER, diner)
    )
}