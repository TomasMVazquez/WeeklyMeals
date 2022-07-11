package com.applications.toms.data.source

import com.applications.toms.data.Either
import com.applications.toms.data.EitherState
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates

interface LocalDataSource {

    suspend fun isEmpty() : Boolean
    suspend fun saveDailyMeals(items: List<Day>): Either<EitherState, ErrorStates>
    suspend fun getDailyMeals(): Either<List<Day>, ErrorStates>
    suspend fun updateDailyMeal(items: List<Day>): Either<EitherState, ErrorStates>

}