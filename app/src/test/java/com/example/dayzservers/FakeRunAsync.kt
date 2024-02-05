package com.example.dayzservers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

internal class FakeRunAsync(private val order: Order) : RunAsync {

    private var cachedResult: Any = Any()
    private var cachedUiBlock: (Any) -> Unit = {}

    override fun <T : Any> runAsync(
        coroutineScope: CoroutineScope,
        backgroundBlock: suspend () -> T,
        uiBlock: (T) -> Unit
    ) = runBlocking {
        order.add(RUN_ASYNC_BACKGROUND)
        cachedResult = backgroundBlock.invoke()
        cachedUiBlock = uiBlock as (Any) -> Unit
    }

    fun pingResult() {
        order.add(RUN_ASYNC_UI)
        cachedUiBlock.invoke(cachedResult)
    }
}