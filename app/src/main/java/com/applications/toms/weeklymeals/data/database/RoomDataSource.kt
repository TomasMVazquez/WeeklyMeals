package com.applications.toms.weeklymeals.data.database

import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.data.toDatabaseModel
import com.applications.toms.weeklymeals.data.toDomainModel
import kotlinx.coroutines.Dispatchers
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
            dao.update(item.toDatabaseModel())
        }
    }

}