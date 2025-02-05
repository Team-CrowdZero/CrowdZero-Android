package com.gdg.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.feature.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CrowdZeroTheme.colors.green600, CrowdZeroTheme.colors.green700)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 170.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(-10.dp)
        ) {
            Text(
                text = stringResource(R.string.splash_title1),
                style = CrowdZeroTheme.typography.h1JalnanGothic,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier.graphicsLayer(rotationZ = -2f)
            )

            Text(
                text = stringResource(R.string.splash_title2),
                style = CrowdZeroTheme.typography.h1JalnanGothic,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier
                    .graphicsLayer(rotationZ = -2f)
                    .padding(start = 5.dp)
            )

            Text(
                text = stringResource(R.string.splash_subtitle),
                style = CrowdZeroTheme.typography.c4Regular,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier.padding(start = 40.dp)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.ic_splash_buildings),
            contentDescription = stringResource(id = R.string.splash_desc),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 17.dp)
        )
    }
}
