package com.example.dayzservers.search.presentation

import com.example.dayzservers.core.UiObservable

interface UiStateObservable : UiObservable<UiState> {

    class Base : UiStateObservable, UiObservable.Base<UiState>(UiState.Empty)
}
