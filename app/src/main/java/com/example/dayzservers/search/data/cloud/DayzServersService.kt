package com.example.dayzservers.search.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface DayzServersService {

    @GET("/servers?filter[game]=dayz&sort=rank")
    fun fetchTopServers(): Call<DayZServersResponse>
}