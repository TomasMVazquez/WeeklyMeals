package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R

@Composable
fun CardDay(day: Day, id: Int, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .padding(all = dimensionResource(id = R.dimen.padding_8))
            .size(size = dimensionResource(id = R.dimen.card_size_small)),
        shape = RoundedCornerShape(size = dimensionResource(id = R.dimen.padding_8)),
        elevation = dimensionResource(id = R.dimen.elevation_4),
        backgroundColor =
        if (day.id == id)
            colorResource(id = R.color.teal_700)
        else
            colorResource(id = R.color.teal_200)
    ) {
        Box(
            modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_4)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(all = dimensionResource(id = R.dimen.padding_4)),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                text = day.day.uppercase().take(1)
            )
        }
    }
}