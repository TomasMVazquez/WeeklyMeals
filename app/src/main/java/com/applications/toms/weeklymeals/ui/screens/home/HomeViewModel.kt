package com.applications.toms.weeklymeals.ui.screens.home

import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.onFailure
import com.applications.toms.usecases.dailymeals.onSuccess
import com.applications.toms.weeklymeals.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDailyMeals: GetDailyMeals,
    uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _week = MutableStateFlow(emptyList<Day>())
    val week: StateFlow<List<Day>> = _week.asStateFlow()

    fun getListFromUseCase(){
        launch {
            getDailyMeals.invoke()
                .onSuccess { result ->
                    _week.value = result.dailyMeals
                }
                .onFailure { result ->
                    /* TODO */
                }
        }
    }

}