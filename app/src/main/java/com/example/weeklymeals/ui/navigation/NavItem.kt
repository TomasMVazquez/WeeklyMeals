package com.example.weeklymeals.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArgs> = emptyList()
){
    object Main: NavItem("main")
    object Edit: NavItem("edit", listOf(NavArgs.ItemMyWeek)){
        fun createRoute(ItemMyWeek: String) = "$baseRoute/$ItemMyWeek"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArgs(val key: String, val navType: NavType<*>) {
    ItemMyWeek("myWeek", NavType.StringType)
}