package com.applications.toms.data.repository

import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.data.source.RemoteDataSource
import com.applications.toms.domain.Day

class DailyMealsRepository(
    private val initialList: List<Day>,
    private val localDataSource: LocalDataSource,
    //private val remoteDataSource: RemoteDataSource
    ) {

    suspend fun saveDailyMeals(items: List<Day>) = localDataSource.saveDailyMeals(items)

    suspend fun getDailyMeals(): List<Day> {
        if (localDataSource.isEmpty()){
            saveDailyMeals(initialList)
        }
        return localDataSource.getDailyMeals()
    }
    suspend fun updateDailyMeal(item: Day) = localDataSource.updateDailyMeal(item)
}
