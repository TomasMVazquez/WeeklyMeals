package com.example.weeklymeals.ui.screens

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.onFailure
import com.applications.toms.usecases.dailymeals.onSuccess
import com.example.weeklymeals.utils.ScopedViewModel
import com.example.weeklymeals.utils.initialState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val context: Context,
    private val getDailyMeals: GetDailyMeals,
    uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _week = MutableLiveData(emptyList<Day>())
    val week: LiveData<List<Day>> = _week

    init {
        getListFromUseCase()
    }

    private fun getListFromUseCase(){
        launch {
            getDailyMeals.invoke()
                .onSuccess { result ->
                    _week.value = result.dailyMeals
                }
                .onFailure { result ->
                    _week.value = initialState(context)
                }
        }
    }
}