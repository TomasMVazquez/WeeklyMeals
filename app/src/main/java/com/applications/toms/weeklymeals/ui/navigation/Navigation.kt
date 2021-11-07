package com.applications.toms.weeklymeals.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.ui.navigation.NavItem.*
import com.applications.toms.weeklymeals.ui.screens.EditScreen
import com.applications.toms.weeklymeals.ui.screens.HomeScreen
import com.applications.toms.weeklymeals.utils.fromDeepLink
import com.applications.toms.weeklymeals.utils.fromJson
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.gson.Gson

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(){
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Main.baseRoute) {
        nav(context,navController)
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
private fun NavGraphBuilder.nav(
    context: Context,
    navController: NavController
) {

    composable(Main) {
        it.arguments
        HomeScreen(
            onEditClick = { myWeek ->
                val jsonString = Gson().toJson(myWeek)
                navController.navigate(Edit.createRoute(jsonString))
            }
        )
    }

    composable(Edit) {
        val myWeek: List<Day> = Gson().fromJson(it.findArg<String>(NavArg.ItemMyWeek))
        EditScreen(
            myWeek = myWeek,
            onBackClick = {
                navController.popBackStack()
            }
        )
    }

    composable(Deeplink) {
        val shareString = it.findArg<String>(NavArg.ItemShareWeek)
        val shareWeek = shareString.fromDeepLink()
        EditScreen(
            myWeek = shareWeek,
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

