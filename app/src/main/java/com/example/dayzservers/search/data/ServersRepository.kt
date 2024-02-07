package com.example.dayzservers.search.data

import com.example.dayzservers.search.data.cloud.ServersCloudDataSource

interface ServersRepository {

    suspend fun fetchTopServers(number: Int): List<DayzServer>

    class Base(
        private val cloudDataSource: ServersCloudDataSource,
    ) : ServersRepository {

        override suspend fun fetchTopServers(number: Int): List<DayzServer> {
            return cloudDataSource.fetchTopServers(number)
        }
    }
}