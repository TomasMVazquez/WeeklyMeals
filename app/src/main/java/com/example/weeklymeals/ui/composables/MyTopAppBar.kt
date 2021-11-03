package com.example.weeklymeals.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weeklymeals.R

@Composable
fun MyMainTopAppBar(title: String, onEditClick: () -> Unit, onShareClick: () -> Unit){
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onEditClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.description_icon_edit)
                )
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(R.string.description_icon_share)
                )
            }
        }
    )
}

@Composable
fun BackTopAppBar(title: String, onBackClick: () -> Unit){
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(
                        R.string.description_arrow_back
                    )
                )
            }
        }
    )
}