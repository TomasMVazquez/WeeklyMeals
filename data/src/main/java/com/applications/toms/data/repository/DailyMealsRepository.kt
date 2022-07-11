package com.applications.toms.data.repository

import com.applications.toms.data.Either
import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates

class DailyMealsRepository(
    private val initialList: List<Day>,
    private val localDataSource: LocalDataSource,
    ) {

    suspend fun saveDailyMeals(items: List<Day>) = localDataSource.saveDailyMeals(items)

    suspend fun getDailyMeals(): Either<List<Day>, ErrorStates> {
        if (localDataSource.isEmpty()){
            saveDailyMeals(initialList)
        }
        return localDataSource.getDailyMeals()
    }
    suspend fun updateDailyMeal(items: List<Day>) = localDataSource.updateDailyMeal(items)
}
