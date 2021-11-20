package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.applications.toms.weeklymeals.R

@Composable
fun SaveFloatingActionButton(enable: Boolean,onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        text = {
            Text(text = stringResource(id = R.string.save))
        },
        icon = {
            Icon(
                imageVector = Icons.Rounded.Save,
                contentDescription = stringResource(R.string.description_icon_save)
            )
        },
        backgroundColor = if (!enable) MaterialTheme.colors.secondary else Color.Gray,
        onClick = { if (!enable) onClick() }
    )
}