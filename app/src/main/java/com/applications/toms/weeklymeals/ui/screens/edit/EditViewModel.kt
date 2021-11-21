package com.applications.toms.weeklymeals.ui.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.applications.toms.domain.Day
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.SaveDailyMeals
import com.applications.toms.usecases.dailymeals.onFailure
import com.applications.toms.usecases.dailymeals.onSuccess
import com.applications.toms.weeklymeals.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class EditViewModel(
    private val getDailyMeals: GetDailyMeals,
    private val saveDailyMeals: SaveDailyMeals,
    uiDispatcher: CoroutineDispatcher)
    : ScopedViewModel(uiDispatcher) {

    private val _weekMeals = MutableLiveData(emptyList<Day>())
    val weekMeals: LiveData<List<Day>> = _weekMeals

    private val _saving = MutableLiveData(false)
    val saving: LiveData<Boolean> = _saving

    fun getShareList(shareList: List<Day>) {
        _weekMeals.value = shareList
    }

    init {
        launch {
            getDailyMeals.invoke()
                .onSuccess { result ->
                    _weekMeals.value = result.dailyMeals
                }
                .onFailure { result ->
                    /* TODO */
                }
        }
    }

    fun saveListToDB(weekMeals: List<Day>) {
        _saving.value = true
        launch {
            saveDailyMeals.invoke(weekMeals)
                .onSuccess { _saving.value = false }
                .onFailure { _saving.value = false }
        }
    }

}