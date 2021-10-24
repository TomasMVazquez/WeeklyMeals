package com.applications.toms.data.repository

import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.data.source.RemoteDataSource
import com.applications.toms.domain.Day

class DailyMealsRepository(
    private val localDataSource: LocalDataSource,
    //private val remoteDataSource: RemoteDataSource
    ) {

    fun saveDailyMeals(items: List<Day>) = localDataSource.saveDailyMeals(items)
    fun getDailyMeals() = localDataSource.getDailyMeals()
    fun updateDailyMeal(item: Day) = localDataSource.updateDailyMeal(item)

}