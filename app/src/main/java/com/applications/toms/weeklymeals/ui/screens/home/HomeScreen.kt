package com.applications.toms.weeklymeals.ui.screens.home

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.composables.MyMainTopAppBar
import com.applications.toms.weeklymeals.ui.composables.MyPager
import com.applications.toms.weeklymeals.ui.composables.RowDayClickable
import com.applications.toms.weeklymeals.ui.composables.SwitchView
import com.applications.toms.weeklymeals.utils.asDeeplinkString
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    onEditClick: () -> Unit,
    homeViewModel: HomeViewModel = getViewModel()
) {

    val state by homeViewModel.state.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.getListFromUseCase()
    }

    val context = LocalContext.current
    val query = state.week.asDeeplinkString()

    Scaffold(
        topBar = {
            MyMainTopAppBar(
                title = state.title,
                onEditClick = onEditClick,
                onShareClick = { onShare(context, query) }
            )
        }
    ) { paddingValues ->
        HomeContent(
            paddingValues = paddingValues,
            myWeek = state.week,
            onTitleChange = {
                homeViewModel.updateTitle(it)
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    myWeek: List<Day>,
    onTitleChange: (String) -> Unit
) {
    var grilledView by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SwitchView(
            checked = grilledView,
            onChecked = { grilledView = it }
        )

        Crossfade(targetState = grilledView) { visible ->
            if (visible)
                //TODO Add new grilled view!
                CircularProgressIndicator()
            else
                WeeklyMealsCards(myWeek = myWeek, onTitleChange = onTitleChange)
        }

    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
private fun WeeklyMealsCards(
    myWeek: List<Day>,
    onTitleChange: (String) -> Unit
) {
    var idPressed by rememberSaveable { mutableStateOf( 0 ) }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var firstEntry by rememberSaveable { mutableStateOf(true) }

    myWeek.firstOrNull() { it.currentDay }?.id?.let { idPressed = it }

    LaunchedEffect(pagerState.currentPage, idPressed) {
        when {
            pagerState.pageCount > 0 && firstEntry -> {
                firstEntry = false
                idPressed = myWeek.first { it.currentDay }.id
                pagerState.scrollToPage(myWeek.first { it.currentDay }.id)
            }
            idPressed != pagerState.currentPage -> {
                pagerState.currentPage.let {
                    idPressed = it
                    onTitleChange(myWeek[it].day)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        MyPager(
            week = myWeek,
            pagerState = pagerState,
        )

        RowDayClickable(
            days = myWeek,
            id = idPressed,
            onClick = { day ->
                coroutineScope.launch {
                    day.id.let {
                        pagerState.animateScrollToPage(it)
                        onTitleChange(myWeek[it].day)
                        idPressed = it
                    }
                }

            }
        )
    }

}

fun onShare(context: Context, query: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        addFlags(FLAG_ACTIVITY_NEW_TASK)
        putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.share_title))
        putExtra(Intent.EXTRA_TEXT, "https://com.applications.toms.weeklymeals/edit/deeplink/${query}")
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(sendIntent, context.getString(R.string.share_title)))
}