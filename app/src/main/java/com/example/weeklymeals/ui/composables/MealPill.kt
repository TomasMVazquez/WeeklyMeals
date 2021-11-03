package com.example.weeklymeals.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.applications.toms.domain.Meal
import com.example.weeklymeals.R
import com.example.weeklymeals.utils.getMealTypeText

@Composable
fun MealPill(meal: Meal){
    var text by rememberSaveable { mutableStateOf(meal.meal) }

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
                onValueChange = {
                    it.let {
                        text = it
                        meal.meal = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(),
                label = {
                  Text(text =  meal.getMealTypeText())
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