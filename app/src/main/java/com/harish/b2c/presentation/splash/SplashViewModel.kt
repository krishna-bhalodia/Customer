package com.harish.b2c.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.b2c.presentation.navigation.NavigationScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
) : ViewModel() {

    // A one-time event for navigation
    private val _navigationEvent = Channel<NavigationScreen>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        initializeApp()
    }

    private fun initializeApp() {
        viewModelScope.launch {
            delay(2000)

            val destination = NavigationScreen.OnBoardScreen
            _navigationEvent.send(destination)
        }
    }
}