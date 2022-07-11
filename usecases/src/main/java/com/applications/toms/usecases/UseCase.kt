package com.applications.toms.usecases

import com.applications.toms.data.Either
import com.applications.toms.data.eitherFailure
import com.applications.toms.domain.ErrorStates
import com.applications.toms.domain.ErrorStates.THROWABLE

abstract class UseCase<T, R> protected constructor(
) {

    protected abstract suspend fun buildUseCase(input: T): Either<R, ErrorStates>

    suspend fun execute(input: T) =
        try {
            buildUseCase(input)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            eitherFailure(THROWABLE)
        }

}