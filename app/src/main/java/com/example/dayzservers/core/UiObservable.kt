package com.example.dayzservers.core

interface UiObservable<T : Any> : UiUpdate<T>, UpdateUiObserver<T> {

    fun clear()

    abstract class Base<T : Any>(private val empty: T) : UiObservable<T> {

        private var cache = empty
        private var observer: UiObserver<T> = object : UiObserver<T> {
            override fun updateUi(data: T) = Unit
        }

        override fun updateUi(data: T) {
            cache = data
        }

        override fun updateUiObserver(observer: UiObserver<T>) {
            this.observer = observer
        }

        override fun clear() {
            cache = empty
        }
    }
}

interface UiUpdate<T : Any> {

    fun updateUi(data: T)
}

interface UiObserver<T : Any> : UiUpdate<T>

interface UpdateUiObserver<T : Any> {

    fun updateUiObserver(observer: UiObserver<T>)
}
