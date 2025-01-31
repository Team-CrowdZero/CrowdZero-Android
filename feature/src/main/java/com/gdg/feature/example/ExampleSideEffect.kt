package com.gdg.feature.example

import androidx.annotation.StringRes

sealed class ExampleSideEffect {
    data object NavigateUp : ExampleSideEffect()
    data class ShowToast(@StringRes val message: Int) : ExampleSideEffect()
}
