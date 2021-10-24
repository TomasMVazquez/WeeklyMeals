package com.applications.toms.domain

data class Meal(
    val mealType: MealType,
    val meal: String
){
    enum class MealType {
        LUNCH,
        DINER
    }
}