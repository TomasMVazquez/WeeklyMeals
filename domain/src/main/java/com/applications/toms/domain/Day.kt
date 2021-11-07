package com.applications.toms.domain

data class Day(
    val id: Int,
    val day: String,
    val lunch: Meal,
    val diner: Meal
)