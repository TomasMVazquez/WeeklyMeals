package com.applications.toms.usecases.dailymeals

import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.domain.Day
import com.applications.toms.usecases.ErrorStates
import com.applications.toms.usecases.dailymeals.SaveDailyMeals.*

class SaveDailyMeals(private val repository: DailyMealsRepository)
    : Either<OkOutput, KoOutput>() {

    data class OkOutput(
        val finish: String
    )

    data class KoOutput(
        val error: ErrorStates
    )

    suspend fun invoke(input: List<Day>): Either<OkOutput, KoOutput> {
        return try {
            input.map {
                repository.updateDailyMeal(it)
            }
            eitherSuccess(OkOutput(""))
        }catch (t: Throwable){
            eitherFailure(KoOutput(ErrorStates.DB_ERROR))
        }
    }

}