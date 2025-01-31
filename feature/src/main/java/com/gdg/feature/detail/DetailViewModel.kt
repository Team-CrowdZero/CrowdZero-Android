package com.gdg.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<DetailSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<DetailSideEffect> get() = _sideEffects

    fun navigateUp() {
        viewModelScope.launch {
            _sideEffects.emit(DetailSideEffect.NavigateUp)
        }
    }
}
