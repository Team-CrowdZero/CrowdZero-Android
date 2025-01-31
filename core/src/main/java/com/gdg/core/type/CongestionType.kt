package com.gdg.core.type

import androidx.compose.ui.graphics.Color
import com.gdg.core.designsystem.theme.Green600
import com.gdg.core.designsystem.theme.Orange
import com.gdg.core.designsystem.theme.Red
import com.gdg.core.designsystem.theme.Yellow

enum class CongestionType(val color: Color, val index: Int) {
    GOOD(Green600, 0),
    NORMAL(Yellow, 1),
    LITTLE_BAD(Orange, 2),
    BAD(Red, 3)
}