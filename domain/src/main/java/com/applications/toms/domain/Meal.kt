package com.applications.toms.domain

data class Meal(
    val mealType: MealType,
    var meal: String
){
    enum class MealType {
        LUNCH,
        DINER
    }
}