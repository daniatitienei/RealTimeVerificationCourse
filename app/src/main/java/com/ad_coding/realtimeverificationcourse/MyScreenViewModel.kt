@file:OptIn(ExperimentalCoroutinesApi::class)

package com.ad_coding.realtimeverificationcourse

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn

class MyScreenViewModel(
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {

    var password by mutableStateOf("")
        private set

    val passwordError =
        snapshotFlow { password }
            .mapLatest { validatePassword.execute(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PasswordValidationState()
            )

    fun changePassword(value: String) {
        password = value
    }
}