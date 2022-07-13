package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MyPager(
    week: List<Day>,
    pagerState: PagerState
) {

    HorizontalPager(
        count = week.size,
        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_48)),
        state = pagerState
    ) { page ->

        var cardLunchFace by remember { mutableStateOf(CardFace.Front) }
        var cardDinerFace by remember { mutableStateOf(CardFace.Front) }

        val dayColorList = listOf(
            colorResource(id = R.color.yellowish),
            colorResource(id = R.color.orangish),
            colorResource(id = R.color.violetish)
        )

        val nightColorList = listOf(
            colorResource(id = R.color.dark_violetish),
            colorResource(id = R.color.violetish),
            colorResource(id = R.color.orangish)
        )

        Column(
            modifier = Modifier.wrapContentHeight(align = Alignment.Top),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            FlipCard(
                cardFace = cardLunchFace,
                onClick = { cardLunchFace = cardLunchFace.next },
                modifier = Modifier,
                front = {
                    FrontBox(
                        meal = week[page].lunch,
                        colorList = dayColorList
                        )
                    },
                back = {
                    BackBox(
                        meal = week[page].lunch,
                        colorList = dayColorList.reversed()
                    )
                },
            )

            FlipCard(
                cardFace = cardDinerFace,
                onClick = { cardDinerFace = cardDinerFace.next },
                modifier = Modifier,
                front = {
                    FrontBox(
                        meal = week[page].diner,
                        colorList = nightColorList
                    )
                },
                back = {
                    BackBox(
                        meal = week[page].diner,
                        colorList = nightColorList.reversed()
                    )
                },
            )
        }
    }
}
