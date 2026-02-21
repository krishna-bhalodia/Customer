package com.harish.b2c.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.b2c.core.components.BaseViewModel
import com.harish.b2c.core.event.UiEventChannel
import com.harish.b2c.presentation.navigation.NavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    uiEventChannel: UiEventChannel
) : BaseViewModel(uiEventChannel) {

    // 1. Tracks if we are still checking the session
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // 2. Holds the route name (e.g., "login_graph" or "home_graph")
    private val _startDestination = MutableStateFlow<String?>(null)
    val startDestination: StateFlow<String?> = _startDestination.asStateFlow()

    init {
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModelScope.launch {
            try {
                delay(1000)
                _startDestination.value = NavigationScreen.HomeScreen.route
            } catch (e: Exception) {
                // Fallback to login on error
            } finally {
                // 3. Stop the loading state to dismiss the splash screen
                _isLoading.value = false
            }
        }
    }
}