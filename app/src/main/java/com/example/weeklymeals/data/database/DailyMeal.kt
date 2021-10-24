package com.example.weeklymeals.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyMeal(
    @PrimaryKey
    val id: Int,
    val day: String,
    val lunch: String,
    val diner: String
)