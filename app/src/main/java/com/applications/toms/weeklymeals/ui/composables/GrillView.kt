package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R

@ExperimentalFoundationApi
@Composable
fun GrillView(
    list: List<Day>
) {
    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(list) {
            DailyMealCard(it)
        }
    }
}

@Composable
fun DailyMealCard(
    day: Day
) {
    Card(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_2)),
        border = BorderStroke(
            width = 1.dp,
            color = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.padding_2)),
                text = day.day,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                color = if (day.currentDay) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_2)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_2)),
                    text = stringResource(id = R.string.card_day_meal_launch),
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = day.lunch.meal,
                    style = MaterialTheme.typography.body1
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.padding_2)),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_2)),
                    text = stringResource(id = R.string.card_day_meal_diner),
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = day.diner.meal,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}