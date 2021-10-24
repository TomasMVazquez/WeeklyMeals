package com.example.weeklymeals.data.database

import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.domain.Day
import com.example.weeklymeals.data.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class RoomDataSource(db: WeeklyMealsDatabase) : LocalDataSource {

    private val dao = db.dao()

    override fun saveDailyMeals(items: List<Day>) {
        TODO("Not yet implemented")
    }

    override fun getDailyMeals(): Flow<List<Day>> {
        return flow {
            dao.getAll().collect { list ->
                list.map { it.toDomainModel() }
            }
        }
    }

    override fun updateDailyMeal(item: Day) {
        TODO("Not yet implemented")
    }

}