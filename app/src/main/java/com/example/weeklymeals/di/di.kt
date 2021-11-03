package com.example.weeklymeals.di

import android.app.Application
import androidx.lifecycle.viewmodel.compose.viewModel
import com.applications.toms.data.repository.DailyMealsRepository
import com.applications.toms.data.source.LocalDataSource
import com.applications.toms.usecases.dailymeals.GetDailyMeals
import com.applications.toms.usecases.dailymeals.SaveDailyMeals
import com.example.weeklymeals.data.database.RoomDataSource
import com.example.weeklymeals.data.database.WeeklyMealsDatabase
import com.example.weeklymeals.ui.screens.EditViewModel
import com.example.weeklymeals.ui.screens.HomeViewModel
import com.example.weeklymeals.utils.initialState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module


fun Application.initDi(){
    startKoin {
        androidLogger(Level.ERROR)
        androidContext(this@initDi)
        modules(listOf(appModule, dataModule, useCasesModule))
    }
}

private val appModule = module {
    single { WeeklyMealsDatabase.build(context = get()) }

    factory<LocalDataSource> { RoomDataSource(db = get()) }
    //factory<RemoteDataSource> { ServerDataSource() }

    single<CoroutineDispatcher> { Dispatchers.Main }
}

private val dataModule = module {
    factory {
        DailyMealsRepository(
            initialList = initialState(get()),
            localDataSource = get(),
        //    remoteDataSource = get()
        )
    }
}

private val useCasesModule = module {
    single { GetDailyMeals(get()) }
    single { SaveDailyMeals(get()) }
    single { HomeViewModel(get(), get()) }
    single { EditViewModel(get(), get()) }
}
