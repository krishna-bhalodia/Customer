package com.harish.b2c.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

    private val _timerValue = MutableStateFlow(20)
    val timerValue: StateFlow<Int> = _timerValue.asStateFlow()

    private val _isResendEnabled = MutableStateFlow(false)
    val isResendEnabled: StateFlow<Boolean> = _isResendEnabled.asStateFlow()

    private var timerJob: Job? = null

    fun updatePhoneNumber(number: String) {
        _phoneNumber.value = number
    }

    fun startTimer() {
        _isResendEnabled.value = false
        _timerValue.value = 20
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_timerValue.value > 0) {
                delay(1000L)
                _timerValue.value -= 1
            }
            _isResendEnabled.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}