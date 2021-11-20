package com.applications.toms.weeklymeals.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.composables.*
import com.applications.toms.weeklymeals.utils.asDeeplinkString
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(onEditClick: (List<Day>) -> Unit, homeViewModel: HomeViewModel = getViewModel()){

    val context = LocalContext.current
    val week by homeViewModel.week.observeAsState(initial = emptyList())
    val firstDay = stringResource(id = R.string.monday)
    var titleDay by rememberSaveable { mutableStateOf(firstDay) }

    homeViewModel.getListFromUseCase()

    val query = week.asDeeplinkString()

    Scaffold(
        topBar = { MyMainTopAppBar(
            title = titleDay,
            onEditClick = { onEditClick(week) },
            onShareClick = { onShare(context, query) }
        ) }
    ) { paddingValues ->
        HomeContent(
            paddingValues = paddingValues,
            myWeek = week,
            onTitleChange = {
                titleDay = it
            }
        )
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeContent(paddingValues: PaddingValues, myWeek: List<Day>, onTitleChange: (String) -> Unit){

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var idPressed by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ){

        val stateId = pagerState.currentPage
        if (stateId != idPressed) {
            idPressed = stateId
            onTitleChange(myWeek[stateId].day)
        }

        MyPager(
            week = myWeek,
            pagerState = pagerState
        )

        RowDayClickable(
            days = myWeek,
            id = idPressed,
            onClick = { day ->
                coroutineScope.launch{
                    day.id.let {
                        pagerState.animateScrollToPage(it)
                    }
                }
            }
        )
    }
}

fun onShare(context: Context,query: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_SUBJECT, "Comartiendo comida de la semana")
        putExtra(Intent.EXTRA_TEXT, "https://com.applications.toms.weeklymeals/edit/deeplink/${query}")
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(sendIntent, "Comartiendo comida de la semana"))
}