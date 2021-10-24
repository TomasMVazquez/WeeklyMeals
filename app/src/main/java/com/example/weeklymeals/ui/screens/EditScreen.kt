package com.example.weeklymeals.ui.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weeklymeals.R
import com.example.weeklymeals.ui.composables.MainAppBar

@Composable
fun EditScreen(){
    Scaffold(
        topBar = {
            MainAppBar(title = stringResource(R.string.edit_title))
        }
    ){ paddingValues ->


    }
}