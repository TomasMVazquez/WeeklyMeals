package com.applications.toms.weeklymeals.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DailyMeal::class], version = 1, exportSchema = false)
abstract class WeeklyMealsDatabase : RoomDatabase(){

    abstract fun dao(): WeeklyMealsDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            WeeklyMealsDatabase::class.java,
            "weekly_meals"
        ).build()
    }
}