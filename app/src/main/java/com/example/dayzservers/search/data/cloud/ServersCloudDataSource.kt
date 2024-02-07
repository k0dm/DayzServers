package com.example.dayzservers.search.data.cloud

import com.example.dayzservers.search.data.DayzServer

interface ServersCloudDataSource {

    suspend fun fetchTopServers(number: Int): List<DayzServer>

    class Base(private val service: DayzServersService) : ServersCloudDataSource {

        override suspend fun fetchTopServers(number: Int): List<DayzServer>  {
            return service.fetchTopServers().execute().body()!!.toListDayzServer()
        }
    }
}