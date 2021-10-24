package com.applications.toms.usecases.dailymeals

import com.applications.toms.data.Either
import com.applications.toms.data.ErrorStates
import com.applications.toms.data.ErrorStates.*
import com.applications.toms.data.eitherFailure
import com.applications.toms.data.eitherSuccess
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.domain.Day
import com.applications.toms.usecases.FlowUseCase
import com.applications.toms.usecases.dailymeals.GetDailyMeals.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class GetDailyMeals(private val repository: DailyMealsRepository):
    FlowUseCase<Unit,Either<OkOutput,KoOutput>>() {

    data class OkOutput(
        val dailyMeals: List<Day>
    )

    data class KoOutput(
        val error: ErrorStates
    )

    override fun prepareFlow(input: Unit): Flow<Either<OkOutput, KoOutput>> = flow {
        repository.getDailyMeals().collect { result ->
            if (result.isEmpty())
                eitherFailure(KoOutput(EMPTY_LIST))
            else
                eitherSuccess(OkOutput(result))
        }
    }

}