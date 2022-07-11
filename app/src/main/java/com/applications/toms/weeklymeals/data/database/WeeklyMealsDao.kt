package com.applications.toms.weeklymeals.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface WeeklyMealsDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg items: DailyMeal): Long

    @Update
    suspend fun update(vararg items: DailyMeal): Long

    @Query("SELECT * FROM DailyMeal ORDER BY id ASC")
    suspend fun getAll(): List<DailyMeal>

    @Query("SELECT COUNT(id) FROM DailyMeal")
    fun count(): Int

}