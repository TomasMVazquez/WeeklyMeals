package com.example.weeklymeals.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.weeklymeals.R

@Composable
fun FabShare(){
    FloatingActionButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(
                dimensionResource(id = R.dimen.zeroDp),
                dimensionResource(id = R.dimen.zeroDp),
                dimensionResource(id = R.dimen.zeroDp),
                dimensionResource(id = R.dimen.fab_bottom_78)
            )
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(R.string.description_icon_share)
        )
    }
}