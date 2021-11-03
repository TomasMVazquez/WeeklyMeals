package com.example.weeklymeals.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.applications.toms.domain.Day
import com.example.weeklymeals.ui.navigation.NavItem.*
import com.example.weeklymeals.ui.screens.EditScreen
import com.example.weeklymeals.ui.screens.HomeScreen
import com.example.weeklymeals.utils.fromJson
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main.route) {
        nav(navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
private fun NavGraphBuilder.nav(
    navController: NavController
) {

    composable(Main) {
        HomeScreen(
            onEditClick = { myWeek ->
                val jsonString = Gson().toJson(myWeek)
                navController.navigate(Edit.createRoute(jsonString))
            },
            onShareClick = {

            }
        )
    }

    composable(Edit) {
        val myWeek: List<Day> = Gson().fromJson(it.findArg<String>(NavArgs.ItemMyWeek))
        EditScreen(
            myWeek = myWeek,
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArgs): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}