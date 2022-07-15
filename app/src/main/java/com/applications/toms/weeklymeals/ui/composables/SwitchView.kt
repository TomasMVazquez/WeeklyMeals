package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.theme.BlueGrey500

@Composable
fun SwitchView(
    checked: Boolean,
    onChecked: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = dimensionResource(id = R.dimen.padding_8)),
            textAlign = TextAlign.End,
            text = stringResource(R.string.switch_view_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = { onChecked(it) },
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = MaterialTheme.colors.primaryVariant,
                checkedThumbColor = MaterialTheme.colors.secondaryVariant
            )
        )
    }
}