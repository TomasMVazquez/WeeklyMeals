package com.applications.toms.weeklymeals.di

import android.app.Application
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.SaveDailyMeals
import com.applications.toms.weeklymeals.data.database.RoomDataSource
import com.applications.toms.weeklymeals.data.database.WeeklyMealsDatabase
import com.applications.toms.weeklymeals.ui.screens.edit.EditViewModel
import com.applications.toms.weeklymeals.ui.screens.home.HomeViewModel
import com.applications.toms.weeklymeals.utils.initialState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


fun Application.initDi(){
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDi)
        modules(listOf(appModule, dataModule, useCasesModule, viewModelModule))
    }
}

private val appModule = module {
    single { WeeklyMealsDatabase.build(context = get()) }

    factory<LocalDataSource> { RoomDataSource(db = get()) }

    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory {
        DailyMealsRepository(
            initialList = initialState(get()),
            localDataSource = get(),
        )
    }
}

private val useCasesModule = module {
    single { GetDailyMeals(get()) }
    single { SaveDailyMeals(get()) }
}

private val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { EditViewModel(get(), get()) }
}
