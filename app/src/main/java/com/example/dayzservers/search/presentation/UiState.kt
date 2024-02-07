package com.example.dayzservers.search.presentation

import com.example.dayzservers.search.domain.DayzServerUi

interface UiState {

    data class Success(private val servers: List<DayzServerUi>): UiState

    data class Error(private val message: String): UiState

    object Empty: UiState
}