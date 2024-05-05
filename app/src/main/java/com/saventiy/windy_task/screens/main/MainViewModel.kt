package com.saventiy.windy_task.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saventiy.windy_task.flow.SummationFlowImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val summationFlow: SummationFlowImpl
) : ViewModel() {

    val uiState: MutableStateFlow<MainViewState> =
        MutableStateFlow(MainViewState(sumStr = ""))

    fun startSummationFlow(n: Int) {
        summationFlow.startFlow(n)
            .onStart {
                uiState.update {
                    MainViewState("")
                }
            }
            .onEach { value ->
                uiState.update { state ->
                    state.copy(sumStr = state.sumStr + " " + value)
                }
            }
            .launchIn(viewModelScope)
    }
}