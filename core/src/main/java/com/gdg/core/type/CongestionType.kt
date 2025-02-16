package com.gdg.core.type

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.gdg.core.R
import com.gdg.core.designsystem.theme.Gray700
import com.gdg.core.designsystem.theme.Green600
import com.gdg.core.designsystem.theme.Orange
import com.gdg.core.designsystem.theme.Red
import com.gdg.core.designsystem.theme.Yellow

enum class CongestionType(
    val color: Color,
    val index: Int,
    @DrawableRes val icon: Int
) {
    GOOD(
        color = Green600,
        index = 0,
        icon = R.drawable.ic_congestion_green
    ),
    NORMAL(
        color = Yellow,
        index = 1,
        icon = R.drawable.ic_congestion_yellow
    ),
    LITTLE_BAD(
        color = Orange,
        index = 2,
        icon = R.drawable.ic_congestion_orange
    ),
    BAD(
        color = Red,
        index = 3,
        icon = R.drawable.ic_congestion_red
    ),
    UNKNOWN(
        color = Gray700,
        index = 4,
        icon = R.drawable.ic_congestion_gray
    )
}