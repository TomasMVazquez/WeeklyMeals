package com.applications.toms.data.source

import com.applications.toms.domain.Day
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun saveDailyMeals(items: List<Day>)
    fun getDailyMeals(): Flow<List<Day>>
    fun updateDailyMeal(item: Day)

}