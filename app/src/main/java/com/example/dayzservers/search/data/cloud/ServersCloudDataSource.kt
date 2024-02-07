package com.example.dayzservers.search.data.cloud

interface ServersCloudDataSource {

    suspend fun fetchTopServers(number: Int): List<DayzServerCloud>

    class Base(private val service: DayzServersService) : ServersCloudDataSource {

        override suspend fun fetchTopServers(number: Int): List<DayzServerCloud>  {
            return service.fetchTopServers().execute().body()!!.toListDayzServerCloud()
        }
    }
}