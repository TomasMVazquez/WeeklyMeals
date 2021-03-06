package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.applications.toms.domain.Meal
import com.applications.toms.weeklymeals.R

@Composable
fun BackBox(meal: Meal, colorList: List<Color>){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = colorList
                ),
                alpha = 0.5F
            )
    )
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = if(meal.meal.isNotEmpty()) meal.meal else stringResource(id = R.string.empty_meal)
        )
    }
}