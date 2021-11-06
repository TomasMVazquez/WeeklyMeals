package com.applications.toms.weeklymeals.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface WeeklyMealsDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg items: DailyMeal)

    @Update
    suspend fun update(item: DailyMeal)

    @Query("SELECT * FROM DailyMeal ORDER BY id ASC")
    suspend fun getAll(): List<DailyMeal>

    @Query("SELECT COUNT(id) FROM DailyMeal")
    fun count(): Int

}