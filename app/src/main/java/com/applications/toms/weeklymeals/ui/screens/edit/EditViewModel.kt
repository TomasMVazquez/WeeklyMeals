package com.applications.toms.weeklymeals.ui.screens.edit

import androidx.lifecycle.viewModelScope
import com.applications.toms.data.onFailure
import com.applications.toms.data.onSuccess
import com.applications.toms.domain.Day
import com.applications.toms.domain.ErrorStates
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.SaveDailyMeals
import com.applications.toms.weeklymeals.ui.composables.SnackBarType
import com.applications.toms.weeklymeals.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditViewModel(
    private val getDailyMeals: GetDailyMeals,
    private val saveDailyMeals: SaveDailyMeals,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    fun getShareList(shareList: List<Day>) {
        _state.update { state ->
            state.copy(
                loading = false,
                ready = true,
                week = shareList.toMutableList()
            )
        }
    }

    init {
        launch {
            getDailyMeals.execute(Unit)
                .onSuccess { result ->
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            week = result
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

    fun saveListToDB(weekMeals: List<Day>) {
        launch {
            saveDailyMeals.execute(weekMeals)
                .onSuccess {
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            ready = false
                        )
                    }
                    emitEffect(Effect.Saved)
                }
                .onFailure {
                    _state.update { state ->
                        state.copy(
                            loading = false,
                            error = it
                        )
                    }
                    emitEffect(Effect.Error(it))
                }
        }
    }

    fun onEditDay(day: Day) {
        _state.update { state ->
            state.copy(
                loading = false,
                ready = true,
                week = state.week.map { if (it.id == day.id) day else it }
            )
        }
    }

    fun addSnackBarType(snackBarType: SnackBarType) {
        _state.update { state ->
            state.copy(
                snackBarType = snackBarType
            )
        }
    }

    private fun emitEffect(effect: Effect) {
        viewModelScope.launch {
            _effect.send(
                effect
            )
        }
    }

    data class State(
        val loading: Boolean = true,
        val ready: Boolean = false,
        val week: List<Day> = emptyList(),
        val error: ErrorStates? = null,
        var snackBarType: SnackBarType = SnackBarType.DEFAULT,
    )

    sealed class Effect {
        object Saved : Effect()
        data class Error(
            val error: ErrorStates?
        ) : Effect()
    }
}