package com.gdg.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<SplashSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects

    fun navigateToMap() {
        viewModelScope.launch {
            _sideEffects.emit(SplashSideEffect.NavigateToMap)
        }
    }
}

sealed class SplashSideEffect {
    data object NavigateToMap : SplashSideEffect()
}