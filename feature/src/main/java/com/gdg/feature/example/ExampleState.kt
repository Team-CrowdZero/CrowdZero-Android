package com.gdg.feature.example

import com.gdg.core.state.UiState
import com.gdg.domain.entity.ExampleEntity

data class ExampleState(
    var followers: UiState<List<ExampleEntity>> = UiState.Empty
)
