package com.applications.toms.weeklymeals.data.database

import com.applications.toms.data.Either
import com.applications.toms.data.EitherState
import com.applications.toms.data.eitherFailure
import com.applications.toms.data.eitherSuccess
import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.weeklymeals.data.toDatabaseModel
import com.applications.toms.weeklymeals.data.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource(db: WeeklyMealsDatabase) : LocalDataSource {

    private val dao = db.dao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { dao.count() <= 0 }

    override suspend fun saveDailyMeals(items: List<Day>): Either<EitherState, ErrorStates> =
        withContext(Dispatchers.IO) {
            val response = items.map { dao.insert(it.toDatabaseModel()) }
            if (response.all { it > 0 } ) eitherSuccess(EitherState.SUCCESS)
            else eitherFailure(ErrorStates.DB_ERROR)
        }

    override suspend fun getDailyMeals(): Either<List<Day>, ErrorStates>  =
        withContext(Dispatchers.IO) {
            val response = dao.getAll().map { it.toDomainModel() }
            if (response.isEmpty()) eitherFailure(ErrorStates.EMPTY_LIST)
            else eitherSuccess(response)
        }

    override suspend fun updateDailyMeal(items: List<Day>): Either<EitherState, ErrorStates> =
        withContext(Dispatchers.IO) {
            val response = items.map { dao.update(it.toDatabaseModel()) }
            if (response.all { it > 0 } ) eitherSuccess(EitherState.SUCCESS)
            else eitherFailure(ErrorStates.DB_ERROR)
        }

}