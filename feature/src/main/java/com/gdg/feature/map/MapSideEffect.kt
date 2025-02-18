package com.gdg.feature.map

import com.gdg.domain.entity.RoadEntity

sealed class MapSideEffect {
    data class NavigateToDetail(val id: Int) : MapSideEffect()
    data class ShowToast(val message: Int) : MapSideEffect()
    data class ShowBottomSheet(val road: RoadEntity?) : MapSideEffect()
}
