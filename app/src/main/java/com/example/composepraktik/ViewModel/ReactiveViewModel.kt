package com.example.composepraktik.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ReactiveViewModel : ViewModel() {


    fun coldFlow(): Flow<Int> = flow {
        emit(1)
        delay(500)
        emit(2)
        delay(500)
        emit(3)
    }

    private val _shared = MutableSharedFlow<String>()
    val shared = _shared.asSharedFlow()

    private val _state = MutableStateFlow(0)
    val state = _state.asStateFlow()

    private val _live = MutableLiveData("Start LiveData")
    val live: LiveData<String> = _live

    private val _channel = Channel<String>()
    val channelFlow = _channel.receiveAsFlow()

    init {
        viewModelScope.launch {
            _shared.emit("SharedFlow: A")
            delay(1000)
            _shared.emit("SharedFlow: B")
        }

        viewModelScope.launch {
            repeat(5) {
                delay(700)
                _state.value = it
            }
        }

        viewModelScope.launch {
            delay(1200)
            _live.postValue("LiveData updated")
        }

        viewModelScope.launch {
            _channel.send("Channel event 1")
            delay(800)
            _channel.send("Channel event 2")
        }
    }
}
