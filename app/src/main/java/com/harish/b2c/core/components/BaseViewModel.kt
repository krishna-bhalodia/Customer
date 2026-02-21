package com.harish.b2c.core.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harish.b2c.core.event.UiEvent
import com.harish.b2c.core.event.UiEventChannel
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val uiEventChannel: UiEventChannel
) : ViewModel() {

    fun showDialog(
        title: String,
        message: String,
        confirmText: String = "OK",
        cancelText: String? = null,
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        viewModelScope.launch {
            uiEventChannel.send(
                UiEvent.ShowDialog(
                    title = title,
                    message = message,
                    confirmText = confirmText,
                    cancelText = cancelText,
                    onConfirm = onConfirm,
                    onCancel = onCancel
                )
            )
        }
    }
}
