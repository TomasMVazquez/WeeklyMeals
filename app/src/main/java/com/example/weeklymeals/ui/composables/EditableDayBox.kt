package com.example.weeklymeals.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.weeklymeals.R

@Composable
fun EditableDayBoxRow(
    titleColumn: @Composable () -> Unit = {},
    contentColumn: @Composable () -> Unit = {}
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_4)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.stroke_1),
            color = Color.LightGray
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_4)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(
                        weight = 1f,
                        fill = true
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    titleColumn()
                }
            }

            Box(
                modifier = Modifier
                    .weight(
                        weight = 2.5f,
                        fill = true
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    contentColumn()
                }
            }
        }
    }
}

@Composable
fun EditableDayBoxColumn(
    titleColumn: @Composable () -> Unit = {},
    contentColumn: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen.padding_4)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.stroke_1),
            color = Color.LightGray
        )
    ){
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(dimensionResource(id = R.dimen.padding_4)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_4)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    titleColumn()
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    contentColumn()
                }
            }
        }
    }
}