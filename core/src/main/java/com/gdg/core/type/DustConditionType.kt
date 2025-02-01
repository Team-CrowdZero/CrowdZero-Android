package com.gdg.core.type

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.gdg.core.R
import com.gdg.core.designsystem.theme.Green600
import com.gdg.core.designsystem.theme.Orange
import com.gdg.core.designsystem.theme.Red

enum class DustConditionType(
    @StringRes val title: Int,
    val color: Color
) {
    GOOD(
        title = R.string.dust_condition_good,
        color = Green600
    ),
    NORMAL(
        title = R.string.dust_condition_normal,
        color = Orange
    ),
    BAD(
        title = R.string.dust_condition_bad,
        color = Red
    )
}
