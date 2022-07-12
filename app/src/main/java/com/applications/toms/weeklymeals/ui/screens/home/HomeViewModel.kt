package com.applications.toms.weeklymeals.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applications.toms.data.onFailure
import com.applications.toms.data.onSuccess
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(
    private val getDailyMeals: GetDailyMeals
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val dayOfWeek by lazy {
        when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> 6
            2 -> 0
            3 -> 1
            4 -> 2
            5 -> 3
            6 -> 4
            7 -> 5
            else -> 0
        }
    }

    fun getListFromUseCase() {
        viewModelScope.launch {
            getDailyMeals.execute(Unit)
                .onSuccess { result ->
                    _state.update { state ->
                        val weeklyMeals = result.map { if (it.id == dayOfWeek) it.copy(currentDay = true) else it }
                        state.copy(
                            loading = false,
                            week = weeklyMeals,
                            title = weeklyMeals.first { it.currentDay }.day
                        )
                    }
                }
                .onFailure { result ->
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            error = result
                        )
                    }
                }
        }
    }

    fun updateTitle(title: String) {
        _state.update { state ->
            state.copy(
                title = title
            )
        }
    }

    data class State(
        val loading: Boolean = true,
        val week: List<Day> = emptyList(),
        val title: String = "",
        val error: ErrorStates? = null
    )

}