package com.gdg.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.core.state.UiState
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.domain.repository.CrowdZeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val crowdZeroRepository: CrowdZeroRepository
) : ViewModel() {

    private val _getScheduleState: MutableStateFlow<UiState<List<ScheduleEntity>>> =
        MutableStateFlow(UiState.Empty)
    val getScheduleState: StateFlow<UiState<List<ScheduleEntity>>> get() = _getScheduleState

    private val _scheduleList: MutableStateFlow<List<ScheduleEntity>> = MutableStateFlow(emptyList())
    val scheduleList: StateFlow<List<ScheduleEntity>> get() = _scheduleList

    fun getAssembly(date: String) {
        viewModelScope.launch {
            _getScheduleState.emit(UiState.Loading)
            crowdZeroRepository.getAssembly(date).fold(
                // 성공했을 때
                onSuccess = {
                    _getScheduleState.emit(UiState.Success(listOf(it)))
                },
                // 실패했을 때
                onFailure = {
                    _getScheduleState.emit(UiState.Failure(it.message.toString()))
                }
            )
        }
    }

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> get() = _selectedDate

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }
}