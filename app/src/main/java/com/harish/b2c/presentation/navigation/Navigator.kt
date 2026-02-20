package com.harish.b2c.presentation.navigation


import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    private val _commands = MutableSharedFlow<NavigationCommand>()
    val commands = _commands.asSharedFlow()

    suspend fun navigate(route: String) {
        _commands.emit(NavigationCommand.Navigate(route))
    }

    suspend fun navigateAndClear(route: String) {
        _commands.emit(NavigationCommand.NavigateAndClear(route))
    }
}

sealed class NavigationCommand {
    data class Navigate(val route: String) : NavigationCommand()
    data class NavigateAndClear(val route: String) : NavigationCommand()
}
