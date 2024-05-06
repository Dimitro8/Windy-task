package com.saventiy.windy_task.flow

import kotlinx.coroutines.flow.Flow

interface SummationFlowInterface {
    fun startFlow(n: Int): Flow<String>
}