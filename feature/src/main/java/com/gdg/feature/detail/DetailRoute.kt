package com.gdg.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
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
fun DetailRoute(
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int,
    navigateUp: () -> Unit
) {
    LaunchedEffect(key1 = detailViewModel.sideEffects) {
        detailViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is DetailSideEffect.NavigateUp -> navigateUp()
            }
        }
    }

    DetailScreen(
        onBackButtonClick = detailViewModel::navigateUp
    )
}

@Composable
fun DetailScreen(
    onBackButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = onBackButtonClick
        ) {
            Text("Go back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CrowdZeroAndroidTheme {
        DetailScreen()
    }
}
