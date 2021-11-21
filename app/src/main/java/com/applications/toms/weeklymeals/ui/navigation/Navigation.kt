package com.applications.toms.weeklymeals.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.applications.toms.weeklymeals.ui.navigation.NavItem.*
import com.applications.toms.weeklymeals.ui.screens.edit.EditScreen
import com.applications.toms.weeklymeals.ui.screens.home.HomeScreen
import com.applications.toms.weeklymeals.utils.fromDeepLink
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Main.baseRoute) {
        nav(navController)
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
private fun NavGraphBuilder.nav(navController: NavController) {

    composable(Main) {
        it.arguments
        HomeScreen(
            onEditClick = {
                navController.navigate(Edit.route)
            }
        )
    }

    composable(Edit) {
        EditScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }

    composable(Deeplink) {
        val shareString = it.findArg<String>(NavArg.ItemShareWeek)
        val shareWeek = shareString.fromDeepLink()
        EditScreen(
            shareList = shareWeek,
            onBackClick = {
                navController.navigateUp()
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
        arguments = navItem.args,
        deepLinks = navItem.deeplinks
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}


