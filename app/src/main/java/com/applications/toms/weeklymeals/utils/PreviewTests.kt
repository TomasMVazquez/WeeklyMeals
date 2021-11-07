package com.applications.toms.weeklymeals.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.applications.toms.domain.Meal
import com.applications.toms.weeklymeals.R

@Preview
@Composable
fun Preview(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.LightGray)
            .padding(dimensionResource(id = R.dimen.padding_4)),
        border = BorderStroke(
            width = dimensionResource(id = R.dimen.stroke_1),
            color = Color.LightGray
        )
    ){
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Yellow)
                .padding(dimensionResource(id = R.dimen.padding_4)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .background(color = Color.Red)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_2)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Miercoles".toUpperCase(Locale.current),
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
                }
            }

            Box(
                modifier = Modifier
                    .background(color = Color.Green)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    MealPillPreview()
                    MealPillPreview()
                }
            }
        }
    }
}

@Composable
fun MealPillPreview(){
    val myMeal = Meal(Meal.MealType.LUNCH,"Almuerzo Meal")
    var text by remember { mutableStateOf(myMeal.meal) }

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_8)),
        shape = CircleShape,
        backgroundColor = Color.LightGray
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.padding_2)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(text =  myMeal.getMealTypeText())
                },
                label = {
                    if (text.isNotEmpty()) Text(text =  myMeal.getMealTypeText())
                },
                maxLines = 2,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                shape = CircleShape,
                trailingIcon = {
                    if (text.isNotEmpty()){
                        IconButton(onClick = { text = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.description_clear_text)
                            )
                        }
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Gray,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}