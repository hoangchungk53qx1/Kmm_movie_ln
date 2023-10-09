package com.chungha.team.survey

import androidx.compose.runtime.Composable
import com.chungha.team.survey.theme.AppTheme

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
//        RootNavGraph()
    }
}
