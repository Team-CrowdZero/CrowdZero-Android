package com.gdg.feature.detail

sealed class DetailSideEffect {
    data object NavigateUp : DetailSideEffect()
}