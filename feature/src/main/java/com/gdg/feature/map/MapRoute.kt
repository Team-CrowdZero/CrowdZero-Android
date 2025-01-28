package com.gdg.feature.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun MapRoute(
    mapViewModel: MapViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    navigateToDetail: (Int) -> Unit
) {
    LaunchedEffect(key1 = mapViewModel.sideEffects) {
        mapViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is MapSideEffect.NavigateToDetail -> navigateToDetail(sideEffect.id)
            }
        }
    }

    MapScreen(
        paddingValues = paddingValues,
        onButtonClick = mapViewModel::navigateToDetail
    )
}

@Composable
fun MapScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onButtonClick: (Int) -> Unit = { }
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .navigationBarsPadding()
            .statusBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Map Screen"
        )
        Button(
            onClick = { onButtonClick(1) }
        ) {
            Text("Go to Detail")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    CrowdZeroAndroidTheme {
        MapScreen()
    }
}