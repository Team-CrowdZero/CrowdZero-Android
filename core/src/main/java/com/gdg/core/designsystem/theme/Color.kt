package com.gdg.core.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

// Gray Scale
val Gray900 = Color(0xFF222222)
val Gray800 = Color(0xFF333333)
val Gray700 = Color(0xFF808080)
val Gray600 = Color(0xFFB1B1B1)
val Gray500 = Color(0xFFE9E9EB)
val Gray400 = Color(0xFFEFF1F3)
val Gray300 = Color(0xFFF8F8FA)
val Shadow = Color(0x40000000)
val White = Color(0xFFFFFFFF)

// Bar Color
val Yellow = Color(0xFFFFD700)
val Orange = Color(0xFFFF8C00)
val Red = Color(0xFFFF4500)

// Green Color
val Green800 = Color(0xFF05668D)
val Green700 = Color(0xFF00BFA6)
val Green600 = Color(0xFF04D181)
val Green500 = Color(0xFF9AE5DA)

// Blue Color
val Blue200 = Color(0xFF73CAED)
val Blue100 = Color(0xFF98D1E8)

@Stable
class CrowdZeroColors(
    gray900: Color,
    gray800: Color,
    gray700: Color,
    gray600: Color,
    gray500: Color,
    gray400: Color,
    gray300: Color,
    shadow: Color,
    white: Color,
    yellow: Color,
    orange: Color,
    red: Color,
    green800: Color,
    green700: Color,
    green600: Color,
    green500: Color,
    blue200: Color,
    blue100: Color
) {
    var gray900 by mutableStateOf(gray900)
        private set
    var gray800 by mutableStateOf(gray800)
        private set
    var gray700 by mutableStateOf(gray700)
        private set
    var gray600 by mutableStateOf(gray600)
        private set
    var gray500 by mutableStateOf(gray500)
        private set
    var gray400 by mutableStateOf(gray400)
        private set
    var gray300 by mutableStateOf(gray300)
        private set
    var shadow by mutableStateOf(shadow)
        private set
    var white by mutableStateOf(white)
        private set
    var yellow by mutableStateOf(yellow)
        private set
    var orange by mutableStateOf(orange)
        private set
    var red by mutableStateOf(red)
        private set
    var green800 by mutableStateOf(green800)
        private set
    var green700 by mutableStateOf(green700)
        private set
    var green600 by mutableStateOf(green600)
        private set
    var green500 by mutableStateOf(green500)
        private set
    var blue200 by mutableStateOf(blue200)
        private set
    var blue100 by mutableStateOf(blue100)
        private set

    fun copy(): CrowdZeroColors = CrowdZeroColors(
        gray900 = gray900,
        gray800 = gray800,
        gray700 = gray700,
        gray600 = gray600,
        gray500 = gray500,
        gray400 = gray400,
        gray300 = gray300,
        shadow = shadow,
        white = white,
        yellow = yellow,
        orange = orange,
        red = red,
        green800 = green800,
        green700 = green700,
        green600 = green600,
        green500 = green500,
        blue200 = blue200,
        blue100 = blue100
    )

    fun update(other: CrowdZeroColors) {
        gray900 = other.gray900
        gray800 = other.gray800
        gray700 = other.gray700
        gray600 = other.gray600
        gray500 = other.gray500
        gray400 = other.gray400
        gray300 = other.gray300
        shadow = other.shadow
        white = other.white
        yellow = other.yellow
        orange = other.orange
        red = other.red
        green800 = other.green800
        green700 = other.green700
        green600 = other.green600
        green500 = other.green500
        blue200 = other.blue200
        blue100 = other.blue100
    }
}

@Composable
fun crowdZeroColors(
    gray900: Color = Gray900,
    gray800: Color = Gray800,
    gray700: Color = Gray700,
    gray600: Color = Gray600,
    gray500: Color = Gray500,
    gray400: Color = Gray400,
    gray300: Color = Gray300,
    shadow: Color = Shadow,
    white: Color = White,
    yellow: Color = Yellow,
    orange: Color = Orange,
    red: Color = Red,
    green800: Color = Green800,
    green700: Color = Green700,
    green600: Color = Green600,
    green500: Color = Green500,
    blue200: Color = Blue200,
    blue100: Color = Blue100
) = CrowdZeroColors(
    gray900 = gray900,
    gray800 = gray800,
    gray700 = gray700,
    gray600 = gray600,
    gray500 = gray500,
    gray400 = gray400,
    gray300 = gray300,
    shadow = shadow,
    white = white,
    yellow = yellow,
    orange = orange,
    red = red,
    green800 = green800,
    green700 = green700,
    green600 = green600,
    green500 = green500,
    blue200 = blue200,
    blue100 = blue100
)
