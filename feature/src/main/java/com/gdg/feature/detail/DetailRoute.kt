package com.gdg.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.feature.R


@Composable
fun DetailRoute(
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int,
    paddingValues: PaddingValues,
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
        paddingValues = paddingValues,
        onBackButtonClick = detailViewModel::navigateUp
    )
}

@Composable
fun DetailScreen(
    paddingValues: PaddingValues = PaddingValues(),
    onBackButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 3.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append("지금의 ")
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                            append("광화문 광장")
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append("은")
                        }
                    },
                    style = CrowdZeroTheme.typography.h2Bold
                )
                Text(
                    text = "인구 혼잡도와 날씨 정보를 확인하세요",
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_pin),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            CrowdZeroTheme.colors.blue100,
                            CrowdZeroTheme.colors.blue200
                        )
                    ),
                    shape = RectangleShape,
                    alpha = 0.5f
                ),
        ) {
            Column {

            }
        }

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
