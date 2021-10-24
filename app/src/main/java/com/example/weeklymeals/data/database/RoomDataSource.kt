package com.example.weeklymeals.data.database

import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.domain.Day
import com.example.weeklymeals.data.toDatabaseModel
import com.example.weeklymeals.data.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RoomDataSource(db: WeeklyMealsDatabase) : LocalDataSource {

    private val dao = db.dao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { dao.count() <= 0 }

    override suspend fun saveDailyMeals(items: List<Day>) {
        withContext(Dispatchers.IO) {
            dao.insertAll(items = items.map { it.toDatabaseModel() }.toTypedArray())
        }
    }

    override suspend fun getDailyMeals(): List<Day>  =
        withContext(Dispatchers.IO) {
            dao.getAll().map { it.toDomainModel() }
        }

    override suspend fun updateDailyMeal(item: Day) {
        withContext(Dispatchers.IO) {

        }
    }

}