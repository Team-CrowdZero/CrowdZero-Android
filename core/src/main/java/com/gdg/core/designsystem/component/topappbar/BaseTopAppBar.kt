package com.gdg.core.designsystem.component.topappbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.R
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.extension.showIf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    route: String? = "",
    isIconVisible: Boolean = false,
    onBackButtonClick: () -> Unit = {}
) {
    val title = when (route) {
        stringResource(R.string.route_map) -> R.string.app_bar_map
        stringResource(R.string.route_calendar) -> R.string.app_bar_calendar
        stringResource(R.string.route_detail) -> R.string.app_bar_detail
        else -> R.string.app_bar_error
    }

    CenterAlignedTopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = stringResource(id = title),
                textAlign = TextAlign.Center,
                color = Black
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.ic_back),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .noRippleClickable { onBackButtonClick() }
                    .showIf(isIconVisible),
                tint = Black
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(White),
        windowInsets = WindowInsets(0)
    )
}

@Preview(showBackground = true)
@Composable
fun BaseTopAppBarPreview() {
    CrowdZeroAndroidTheme {
        BaseTopAppBar(
            route = "Calendar"
        )
    }
}
