package com.applications.toms.usecases.dailymeals

import com.applications.toms.usecases.ErrorStates
import com.applications.toms.usecases.ErrorStates.*
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals.*

class GetDailyMeals(private val repository: DailyMealsRepository):
    Either<OkOutput,KoOutput>() {

    data class OkOutput(
        val dailyMeals: List<Day>
    )

    data class KoOutput(
        val error: ErrorStates
    )

    suspend fun invoke(): Either<OkOutput, KoOutput> {
        val list = repository.getDailyMeals()
        return if (list.isEmpty())
            eitherFailure(KoOutput(EMPTY_LIST))
        else
            eitherSuccess(OkOutput(list))
    }

}