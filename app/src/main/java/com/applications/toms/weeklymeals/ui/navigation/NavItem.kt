package com.applications.toms.weeklymeals.ui.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

const val MAIN_SCREEN = "main"
const val EDIT_SCREEN = "edit"
const val DEEPLINK_EDIT = "edit/deeplink"

const val URI = "https://com.applications.toms.weeklymeals"

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList(),
    val deeplinks: List<NavDeepLink> = emptyList()
){

    object Main: NavItem(
        baseRoute = MAIN_SCREEN,
    )

    object Edit: NavItem(
        baseRoute = EDIT_SCREEN,
        navArgs = listOf(NavArg.ItemMyWeek)
    ){
        fun createRoute(ItemMyWeek: String) = "$baseRoute/$ItemMyWeek"
    }

    object Deeplink: NavItem(
        baseRoute = DEEPLINK_EDIT,
        navArgs = listOf(NavArg.ItemShareWeek),
        deeplinks = listOf(
            navDeepLink {
                uriPattern = "$URI/$DEEPLINK_EDIT/{shareWeek}"
            }
        )
    )

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

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemMyWeek("myWeek", NavType.StringType),
    ItemShareWeek("shareWeek", NavType.StringType)
}


