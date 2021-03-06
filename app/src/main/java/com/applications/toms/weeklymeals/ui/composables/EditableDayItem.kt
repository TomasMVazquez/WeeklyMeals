package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.sp
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R

@ExperimentalComposeUiApi
@Composable
fun EditableDayItem(day: Day, onDayChange: (Day) -> Unit){

    EditableDayBoxColumn(
        titleColumn = {
            Text(
                text = day.day.toUpperCase(Locale.current),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = Color.DarkGray,
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                )
            )
        },
        contentColumn = {
            MealPill(
                meal = day.lunch,
                onMealChange = {
                    day.lunch.meal = it.meal
                    onDayChange(day)
                }
            )
            MealPill(
                meal = day.diner,
                onMealChange = {
                    day.diner.meal = it.meal
                    onDayChange(day)
                }
            )
        },
        modifier =  if (day.id == 6) Modifier.padding(
            start = dimensionResource(id = R.dimen.zeroDp),
            top = dimensionResource(id = R.dimen.zeroDp),
            end = dimensionResource(id = R.dimen.zeroDp),
            bottom = dimensionResource(id = R.dimen.padding_56)
        ) else Modifier
    )
}