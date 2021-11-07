package com.applications.toms.data.source

import com.applications.toms.domain.Day
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun isEmpty() : Boolean
    suspend fun saveDailyMeals(items: List<Day>)
    suspend fun getDailyMeals(): List<Day>
    suspend fun updateDailyMeal(item: Day)

}