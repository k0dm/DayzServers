package com.example.dayzservers

import org.junit.Assert.assertEquals

internal class FakeUiObservable<T : Any>(private val emptyUiState: T, private val order: Order) : UiObservable<T> {

    private var cachedUiState = emptyUiState
    private var cachedUiObserver: UiObserver<T> = object : UiObserver<T> {
        override fun updateUi(data: T) = Unit
    }

    override fun updateUi(data: T) {
        order.add(OBSERVABLE_UPDATE)
        cachedUiState = data
        cachedUiObserver.updateUi(data)
    }

    fun checkUiState(data: T) = assertEquals(data,cachedUiState)

    override fun updateUiObserver(uiObserver: UiObserver<T>) {
        order.add(OBSERVABLE_UPDATE_OBSERVER)
        cachedUiObserver = uiObserver
        cachedUiObserver.updateUi(cachedUiState)
    }

    fun checkUiObserver(uiObserver: UiObserver<T>) = assertEquals(uiObserver, cachedUiObserver)

    override fun clear() {
        order.add(OBSERVABLE_CLEAR)
        cachedUiState = emptyUiState
    }
}