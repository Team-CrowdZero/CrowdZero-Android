package com.gdg.feature.map

sealed class MapSideEffect {
    data class NavigateToDetail(val id: Int) : MapSideEffect()
    data class ShowToast(val message: Int) : MapSideEffect()
}
