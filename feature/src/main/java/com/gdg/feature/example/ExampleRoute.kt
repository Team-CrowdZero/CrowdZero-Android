package com.gdg.feature.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.extension.toast
import com.gdg.core.state.UiState
import com.gdg.domain.entity.ExampleEntity
import okhttp3.internal.toImmutableList

@Composable
fun ExampleRoute(
    navigateUp: () -> Unit,
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by exampleViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        exampleViewModel.getFollowers(2)
    }

    LaunchedEffect(key1 = exampleViewModel.sideEffects) {
        exampleViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is ExampleSideEffect.ShowToast -> {
                    context.toast(sideEffect.message)
                }

                is ExampleSideEffect.NavigateUp -> navigateUp()
            }
        }
    }

    when (state.followers) {
        is UiState.Empty -> {
            // Show empty
        }

        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Gray
                )
            }
        }

        is UiState.Success -> {
            ExampleScreen(
                followers = (state.followers as UiState.Success<List<ExampleEntity>>).data.toImmutableList()
            )
        }

        is UiState.Failure -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "서버 연결에 실패했어요 ㅠ"
                )
            }
        }
    }
}

@Composable
fun ExampleScreen(
    followers: List<ExampleEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(followers, key = { follower -> follower.id }) { follower ->
                ExampleItem(follower)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExampleRoutePreview() {
    CrowdZeroAndroidTheme {
        ExampleScreen(
            followers = emptyList()
        )
    }
}
