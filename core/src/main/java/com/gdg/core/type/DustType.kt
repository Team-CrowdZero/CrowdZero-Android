package com.gdg.core.type

import androidx.annotation.StringRes
import com.gdg.core.R

enum class DustType(
    @StringRes val title: Int
) {
    FINE(title = R.string.dust_fine),
    ULTRA_FINE(title = R.string.dust_ultra_fine),
}
