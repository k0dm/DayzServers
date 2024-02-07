package com.example.dayzservers.search.data.cloud

data class DayzServerCloud(
    private val id: Long,
    private val name: String,
    private val ip: String,
    private val port: Int,
    private val players: Int,
    private val maxPlayers: Int,
    private val rank: Int,
    private val status: String,
    private val version: String,
    private val password: Boolean,
    private val official: Boolean,
    private val time: String,
    private val thirdPerson: Boolean,
    private val modded: Boolean,
    private val modIds: List<Long>,
    private val modNames: List<String>,
    private val country: String,
)