package com.applications.toms.weeklymeals.ui.screens.edit

import com.applications.toms.data.onFailure
import com.applications.toms.data.onSuccess
import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.SaveDailyMeals
import com.applications.toms.weeklymeals.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditViewModel(
    private val getDailyMeals: GetDailyMeals,
    private val saveDailyMeals: SaveDailyMeals,
    uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _weekMeals: MutableStateFlow<MutableList<Day>> = MutableStateFlow(emptyList<Day>().toMutableList())
    val weekMeals: StateFlow<List<Day>> = _weekMeals.asStateFlow()

    private val _saving = MutableStateFlow(false)
    val saving: StateFlow<Boolean> = _saving.asStateFlow()

    fun getShareList(shareList: List<Day>) {
        _weekMeals.value = shareList.toMutableList()
    }

    init {
        launch {
            getDailyMeals.execute(Unit)
                .onSuccess { result ->
                    _weekMeals.value = result.toMutableList()
                }
                .onFailure { result ->
                    /* TODO */
                }
        }
    }

    fun saveListToDB(weekMeals: List<Day>) {
        _saving.value = true
        launch {
            saveDailyMeals.execute(weekMeals)
                .onSuccess { _saving.value = false }
                .onFailure { _saving.value = false }
        }
    }

    fun onEditDay(day: Day) {
        _weekMeals.value.set(day.id,day)
    }

}