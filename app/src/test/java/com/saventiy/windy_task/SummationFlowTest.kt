package com.saventiy.windy_task

import com.saventiy.windy_task.flow.SummationFlowImpl
import junit.framework.TestCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(Parameterized::class)
internal class SummationFlowTest(private val params: Pair<List<Int>, Int>) {

    @Test
    fun `WHEN SummationFlow is correct`(): Unit = runTest {
        val (inputList, expectedSum) = params
        val result = SummationFlowImpl().startFlow(inputList.size).toList()

        TestCase.assertEquals(expectedSum, result.size)
        result.forEachIndexed { index, value ->
            TestCase.assertEquals(inputList[index], value.toInt())
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun build(): Collection<Pair<List<Int>, Int>> {
            return listOf(
                Pair(listOf(1), 1),
                Pair(listOf(1, 3), 2),
                Pair(listOf(1, 3, 6), 3),
                Pair(listOf(1, 3, 6, 10, 15, 21, 28), 7),
            )
        }
    }
}
