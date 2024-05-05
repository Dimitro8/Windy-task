package com.saventiy.windy_task.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    when (val result = uiState.value) {
        else -> MainView(modifier = modifier, viewModel = viewModel, result.sumStr)
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier, viewModel: MainViewModel, sumStr: String) {
    val flowNumbers = remember { mutableStateOf(("")) }
    LazyColumn(modifier = modifier) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    modifier = modifier.fillMaxWidth(),
                    value = flowNumbers.value,
                    onValueChange = {
                        flowNumbers.value = it
                    },
                )
                Button(onClick = { viewModel.startSummationFlow(flowNumbers.value.toInt()) }) {
                    Text(text = "Start")
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = sumStr)
            }
        }
    }
}