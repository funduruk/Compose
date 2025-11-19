package com.example.composepraktik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FoxViewModel : ViewModel() {

    private val _fox = MutableStateFlow<Fox?>(null)
    val fox: StateFlow<Fox?> = _fox

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadFox() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                _fox.value = FoxApiClient.api.getRandomFox()
            } catch (e: Exception) {
                _error.value = "Ошибка: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
