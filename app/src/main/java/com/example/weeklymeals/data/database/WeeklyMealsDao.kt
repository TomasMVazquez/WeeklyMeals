package com.example.weeklymeals.data.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyMealsDao {
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg items: DailyMeal)

    @Update
    fun update(item: DailyMeal)

    @Query("SELECT * FROM DailyMeal ORDER BY id ASC")
    fun getAll(): Flow<List<DailyMeal>>

}