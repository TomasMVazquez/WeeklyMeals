package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.applications.toms.domain.Day
import com.applications.toms.weeklymeals.R
import kotlinx.coroutines.launch

@Composable
fun RowDayClickable(days: List<Day>, id: Int, onClick: (Day) -> Unit) {

    LazyRow(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.padding_8)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        items(days){
            CardDay(
                day = it,
                id = id,
                modifier = Modifier
                    .clickable {
                        onClick(it)
                    }
            )
        }
    }
}