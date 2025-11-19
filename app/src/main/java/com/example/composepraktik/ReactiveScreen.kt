package com.example.composepraktik

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composepraktik.ViewModel.ReactiveViewModel

@Composable
fun ReactiveScreen(viewModel: ReactiveViewModel = viewModel()) {

    var rememberCounter by remember { mutableStateOf(0) }

    val stateValue by viewModel.state.collectAsState()

    val liveDataValue by viewModel.live.observeAsState("")

    val sharedValue = remember { mutableStateOf("") }
    LaunchedEffect(true) {
        viewModel.shared.collect {
            sharedValue.value = it
        }
    }

    val channelValue = remember { mutableStateOf("") }
    LaunchedEffect(true) {
        viewModel.channelFlow.collect {
            channelValue.value = it
        }
    }

    val coldFlowValue = remember { mutableStateOf("") }
    LaunchedEffect(true) {
        viewModel.coldFlow().collect {
            coldFlowValue.value = "Cold flow: $it"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text("Flow: $coldFlowValue")
        Text("SharedFlow: ${sharedValue.value}")
        Text("StateFlow: $stateValue")
        Text("LiveData: $liveDataValue")
        Text("Channel: ${channelValue.value}")

        Divider()

        Text("remember counter: $rememberCounter")
        Button(onClick = { rememberCounter++ }) {
            Text("Increase remember()")
        }
    }
}
