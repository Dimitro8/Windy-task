package com.saventiy.windy_task.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.runningReduce
import java.math.BigInteger

class SummationFlowImpl : SummationFlowInterface {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun startFlow(n: Int): Flow<String> {
        return Array(n) { index ->
            flow {
                delay((index + 1) * 100L)
                emit(index + 1L)
            }
        }
            .asFlow()
            .flatMapMerge { value ->
                value.map { BigInteger.valueOf(it) }
            }
            .runningReduce { prev, next -> prev + next }
            .map {
                delay(100)
                it.toString()
            }
    }
}