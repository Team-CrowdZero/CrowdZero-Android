package com.gdg.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gdg.core.R

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
    h1JalnanGothic: TextStyle,
    h1Regular: TextStyle,
    h2Bold: TextStyle,
    h3Bold: TextStyle,
    h3Regular: TextStyle,
    h3JalnanGothic: TextStyle,
    h4JalnanGothic: TextStyle,
    h5Regular: TextStyle,
    h5Medium: TextStyle,
    h5Bold: TextStyle,
    c1SemiBold: TextStyle,
    c2Medium1: TextStyle,
    c2Medium2: TextStyle,
    c2JalnanGothic: TextStyle,
    c3Medium: TextStyle,
    c3Regular: TextStyle,
    c3Bold: TextStyle,
    c4SemiBold: TextStyle,
    c4Regular: TextStyle,
    c4JalnanGothic: TextStyle,
    c5SemiBold: TextStyle
) {
    var h1JalnanGothic: TextStyle by mutableStateOf(h1JalnanGothic)
        internal set
    var h1Regular: TextStyle by mutableStateOf(h1Regular)
        internal set
    var h2Bold: TextStyle by mutableStateOf(h2Bold)
        internal set
    var h3Bold: TextStyle by mutableStateOf(h3Bold)
        internal set
    var h3Regular: TextStyle by mutableStateOf(h3Regular)
        internal set
    var h3JalnanGothic: TextStyle by mutableStateOf(h3JalnanGothic)
        internal set
    var h4JalnanGothic: TextStyle by mutableStateOf(h4JalnanGothic)
        internal set
    var h5Regular: TextStyle by mutableStateOf(h5Regular)
        internal set
    var h5Medium: TextStyle by mutableStateOf(h5Medium)
        internal set
    var h5Bold: TextStyle by mutableStateOf(h5Bold)
        internal set
    var c1SemiBold: TextStyle by mutableStateOf(c1SemiBold)
        internal set
    var c2Medium1: TextStyle by mutableStateOf(c2Medium1)
        internal set
    var c2Medium2: TextStyle by mutableStateOf(c2Medium2)
        internal set
    var c2JalnanGothic: TextStyle by mutableStateOf(c2JalnanGothic)
        internal set
    var c3Medium: TextStyle by mutableStateOf(c3Medium)
        internal set
    var c3Regular: TextStyle by mutableStateOf(c3Regular)
        internal set
    var c3Bold: TextStyle by mutableStateOf(c3Bold)
        internal set
    var c4SemiBold: TextStyle by mutableStateOf(c4SemiBold)
        internal set
    var c4Regular: TextStyle by mutableStateOf(c4Regular)
        internal set
    var c4JalnanGothic: TextStyle by mutableStateOf(c4JalnanGothic)
        internal set
    var c5SemiBold: TextStyle by mutableStateOf(c5SemiBold)
        internal set

    fun copy(
        h1JalnanGothic: TextStyle = this.h1JalnanGothic,
        h1Regular: TextStyle = this.h1Regular,
        h2Bold: TextStyle = this.h2Bold,
        h3Bold: TextStyle = this.h3Bold,
        h3Regular: TextStyle = this.h3Regular,
        h3JalnanGothic: TextStyle = this.h3JalnanGothic,
        h4JalnanGothic: TextStyle = this.h4JalnanGothic,
        h5Regular: TextStyle = this.h5Regular,
        h5Medium: TextStyle = this.h5Medium,
        h5Bold: TextStyle = this.h5Bold,
        c1SemiBold: TextStyle = this.c1SemiBold,
        c2Medium1: TextStyle = this.c2Medium1,
        c2Medium2: TextStyle = this.c2Medium2,
        c2JalnanGothic: TextStyle = this.c2JalnanGothic,
        c3Medium: TextStyle = this.c3Medium,
        c3Regular: TextStyle = this.c3Regular,
        c3Bold: TextStyle = this.c3Bold,
        c4SemiBold: TextStyle = this.c4SemiBold,
        c4Regular: TextStyle = this.c4Regular,
        c4JalnanGothic: TextStyle = this.c4JalnanGothic,
        c5SemiBold: TextStyle = this.c5SemiBold
    ): CrowdZeroTypography = CrowdZeroTypography(
        h1JalnanGothic = h1JalnanGothic,
        h1Regular = h1Regular,
        h2Bold = h2Bold,
        h3Bold = h3Bold,
        h3Regular = h3Regular,
        h3JalnanGothic = h3JalnanGothic,
        h4JalnanGothic = h4JalnanGothic,
        h5Regular = h5Regular,
        h5Medium = h5Medium,
        h5Bold = h5Bold,
        c1SemiBold = c1SemiBold,
        c2Medium1 = c2Medium1,
        c2Medium2 = c2Medium2,
        c2JalnanGothic = c2JalnanGothic,
        c3Medium = c3Medium,
        c3Regular = c3Regular,
        c3Bold = c3Bold,
        c4SemiBold = c4SemiBold,
        c4Regular = c4Regular,
        c4JalnanGothic = c4JalnanGothic,
        c5SemiBold = c5SemiBold
    )

    fun update(other: CrowdZeroTypography) {
        h1JalnanGothic = other.h1JalnanGothic
        h1Regular = other.h1Regular
        h2Bold = other.h2Bold
        h3Bold = other.h3Bold
        h3Regular = other.h3Regular
        h3JalnanGothic = other.h3JalnanGothic
        h4JalnanGothic = other.h4JalnanGothic
        h5Regular = other.h5Regular
        h5Medium = other.h5Medium
        h5Bold = other.h5Bold
        c1SemiBold = other.c1SemiBold
        c2Medium1 = other.c2Medium1
        c2Medium2 = other.c2Medium2
        c2JalnanGothic = other.c2JalnanGothic
        c3Medium = other.c3Medium
        c3Regular = other.c3Regular
        c3Bold = other.c3Bold
        c4SemiBold = other.c4SemiBold
        c4Regular = other.c4Regular
        c4JalnanGothic = other.c4JalnanGothic
        c5SemiBold = other.c5SemiBold
    }
}

fun crowdZeroTextStyle(
    fontFamily: FontFamily,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    letterSpacing: TextUnit = 0.sp
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

@Composable
fun crowdZeroTypography(): CrowdZeroTypography {
    return CrowdZeroTypography(
        h1JalnanGothic = crowdZeroTextStyle(
            fontFamily = JalnanGothic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 60.sp,
            lineHeight = 60.sp
        ),
        h1Regular = crowdZeroTextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 60.sp,
            lineHeight = 60.sp,
            letterSpacing = 1.2.sp
        ),
        h2Bold = crowdZeroTextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 25.sp
        ),
        h3Bold = crowdZeroTextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        h3Regular = crowdZeroTextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp
        ),
        h3JalnanGothic = crowdZeroTextStyle(
            fontFamily = JalnanGothic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.sp
        ),
        h4JalnanGothic = crowdZeroTextStyle(
            fontFamily = JalnanGothic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 26.sp
        ),
        h5Regular = crowdZeroTextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            lineHeight = 21.sp
        ),
        h5Medium = crowdZeroTextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        h5Bold = crowdZeroTextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp
        ),
        c1SemiBold = crowdZeroTextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        c2Medium1 = crowdZeroTextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 17.sp
        ),
        c2Medium2 = crowdZeroTextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 12.sp
        ),
        c2JalnanGothic = crowdZeroTextStyle(
            fontFamily = JalnanGothic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 17.sp
        ),
        c3Medium = crowdZeroTextStyle(
            fontFamily = PretendardMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 11.sp
        ),
        c3Regular = crowdZeroTextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 15.sp
        ),
        c3Bold = crowdZeroTextStyle(
            fontFamily = PretendardBold,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            lineHeight = 11.sp,
            letterSpacing = 0.22.sp
        ),
        c4SemiBold = crowdZeroTextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 10.sp
        ),
        c4Regular = crowdZeroTextStyle(
            fontFamily = PretendardRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 14.sp
        ),
        c4JalnanGothic = crowdZeroTextStyle(
            fontFamily = JalnanGothic,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            lineHeight = 14.sp,
            letterSpacing = 0.5.sp
        ),
        c5SemiBold = crowdZeroTextStyle(
            fontFamily = PretendardSemiBold,
            fontWeight = FontWeight.SemiBold,
            fontSize = 8.sp,
            lineHeight = 11.sp
        )
    )
}
