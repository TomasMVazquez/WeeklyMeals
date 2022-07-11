package com.applications.toms.usecases.dailymeals

import com.applications.toms.data.Either
import com.applications.toms.data.EitherState
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.usecases.UseCase

class SaveDailyMeals(
    private val repository: DailyMealsRepository
) : UseCase<List<Day>, EitherState>() {

    override suspend fun buildUseCase(input: List<Day>): Either<EitherState, ErrorStates> =
        repository.updateDailyMeal(input)

}