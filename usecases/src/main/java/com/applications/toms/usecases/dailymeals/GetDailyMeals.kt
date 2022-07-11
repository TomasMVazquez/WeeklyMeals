package com.applications.toms.usecases.dailymeals

import com.applications.toms.data.Either
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.usecases.UseCase

class GetDailyMeals(
    private val repository: DailyMealsRepository
) : UseCase<Unit, List<Day>>() {

    override suspend fun buildUseCase(input: Unit): Either<List<Day>, ErrorStates> =
        repository.getDailyMeals()

}