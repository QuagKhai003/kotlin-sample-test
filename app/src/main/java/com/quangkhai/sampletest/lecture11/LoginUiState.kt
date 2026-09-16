package com.quangkhai.sampletest.lecture11

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    object Error : LoginUiState()
}