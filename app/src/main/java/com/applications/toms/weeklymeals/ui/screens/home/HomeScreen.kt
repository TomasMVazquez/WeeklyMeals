package com.applications.toms.weeklymeals.ui.screens.home

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.ui.composables.*
import com.applications.toms.weeklymeals.utils.asDeeplinkString
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate
import java.util.*

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen(onEditClick: () -> Unit, homeViewModel: HomeViewModel = getViewModel()){

    val context = LocalContext.current
    val week by homeViewModel.week.collectAsState()
    var titleDay by rememberSaveable { mutableStateOf("") }

    homeViewModel.getListFromUseCase()

    val query = week.asDeeplinkString()

    Scaffold(
        topBar = { MyMainTopAppBar(
            title = titleDay,
            onEditClick = { onEditClick() },
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

    val dayOfWeek: Int = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
        1 -> 6
        2 -> 0
        3 -> 1
        4 -> 2
        5 -> 3
        6 -> 4
        7 -> 5
        else -> 0
    }

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    var idPressed by rememberSaveable { mutableStateOf(0) }
    var firstEntry by rememberSaveable { mutableStateOf(true) }

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
            pagerState = pagerState,
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

        if (pagerState.pageCount > 0 && firstEntry){
            firstEntry = false
            coroutineScope.launch {
                pagerState.scrollToPage(dayOfWeek)
            }
        }
    }
}

fun onShare(context: Context,query: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        addFlags(FLAG_ACTIVITY_NEW_TASK)
        putExtra(Intent.EXTRA_SUBJECT, "Comartiendo comida de la semana")
        putExtra(Intent.EXTRA_TEXT, "https://com.applications.toms.weeklymeals/edit/deeplink/${query}")
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(sendIntent, "Comartiendo comida de la semana"))
}