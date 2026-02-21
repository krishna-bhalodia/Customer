package com.harish.b2c.core.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

// Sealed class to define the types of events we can show
sealed class UiEvent {

    object NavigateUp : UiEvent()
    data class ShowDialog(
        val title: String,
        val message: String,
        val confirmText: String = "OK",
        val cancelText: String? = null,        // null = hide cancel button
        val onConfirm: (() -> Unit)? = null,
        val onCancel: (() -> Unit)? = null
    ) : UiEvent()

    data class ShowSnackBar(val message: String) : UiEvent()

}

@Singleton
class UiEventChannel @Inject constructor() {

    private val _uiEventFlow = MutableSharedFlow<UiEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    suspend fun send(event: UiEvent) {
        _uiEventFlow.emit(event)
    }
}
