package com.gdg.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gdg.core.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val PretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal))
val PretendardSemiBold =
    FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal))
val PretendardMedium =
    FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal))
val PretendardRegular =
    FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal))
val JalnanGothic = FontFamily(Font(R.font.jalnan_gothic, FontWeight.SemiBold, FontStyle.Normal))

@Stable
class CrowdZeroTypography internal constructor(

)