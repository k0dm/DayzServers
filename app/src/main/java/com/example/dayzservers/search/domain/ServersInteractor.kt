package com.example.dayzservers.search.domain

import com.example.dayzservers.search.data.ServersRepository
import com.example.dayzservers.search.presentation.UiState
import com.example.dayzservers.search.presentation.UiStateObservable
import java.net.UnknownHostException

interface ServersInteractor {

    suspend fun topServers(number: Int = 10): LoadResult

    class Base(private val repository: ServersRepository) : ServersInteractor {

        override suspend fun topServers(number: Int): LoadResult {
            return try {
                val servers = repository.fetchTopServers(number)
                val domainServers = servers.map { dayzServer ->
                    // TODO: find in cacheDataSource whether the server is favorite
                    dayzServer.toDomain(isFavorite = false)
                }
                LoadResult.Success(servers = domainServers)
            } catch (e: Exception) {
                val message = if (e is UnknownHostException)
                    "No internet connection"
                else
                    ""
                LoadResult.Error(message = message)
            }
        }
    }
}

interface LoadResult {

    fun map(observable: UiStateObservable)

    data class Success(private val servers: List<DayzServerDomain>) : LoadResult {

        override fun map(observable: UiStateObservable) {
            observable.updateUi(UiState.Success(servers.map { it.toUi() }))
        }
    }

    data class Error(private val message: String) : LoadResult {
        override fun map(observable: UiStateObservable) {
            observable.updateUi(UiState.Error(message = message))
        }
    }
}

