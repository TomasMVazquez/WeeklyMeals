package com.applications.toms.weeklymeals.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.applications.toms.weeklymeals.R
import com.applications.toms.weeklymeals.ui.theme.ErrorRed
import com.applications.toms.weeklymeals.ui.theme.Info
import com.applications.toms.weeklymeals.ui.theme.Shapes
import com.applications.toms.weeklymeals.ui.theme.Success
import com.applications.toms.weeklymeals.ui.theme.Warning

enum class SnackBarType {
    SUCCESS,
    ERROR,
    INFO,
    WARNING,
    DEFAULT
}

@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackBarType: SnackBarType = SnackBarType.DEFAULT,
    onDismiss: () -> Unit = { }
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16)),
                shape = Shapes.medium,
                backgroundColor = when(snackBarType) {
                    SnackBarType.SUCCESS -> Success
                    SnackBarType.ERROR -> ErrorRed
                    SnackBarType.INFO -> Info
                    SnackBarType.WARNING -> Warning
                    SnackBarType.DEFAULT -> SnackbarDefaults.backgroundColor
                },
                content = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2
                    )
                },
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = actionLabel,
                                color= MaterialTheme.colors.primary,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Bottom)
    )
}