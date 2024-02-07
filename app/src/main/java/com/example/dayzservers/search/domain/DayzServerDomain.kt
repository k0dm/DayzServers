package com.example.dayzservers.search.domain

data class DayzServerDomain(
    private val id: Long = -1,
    private val name: String = "",
    private val ip: String = "",
    private val port: Int = 0,
    private val players: Int = 0,
    private val maxPlayers: Int = 0,
    private val rank: Int = 0,
    private val status: String = "",
    private val version: String = "",
    private val password: Boolean = false,
    private val official: Boolean = false,
    private val time: String = "",
    private val thirdPerson: Boolean = false,
    private val modded: Boolean = false,
    private val modIds: List<Long> = emptyList(),
    private val modNames: List<String> = emptyList(),
    private val country: String = "",
    private val isFavorite: Boolean = false
) {

    fun toUi() = DayzServerUi(
        id = id,
        name = name,
        ip = ip,
        port = port,
        players = players,
        maxPlayers = maxPlayers,
        rank = rank,
        status = status,
        version = version,
        password = password,
        official = official,
        time = time,
        thirdPerson = thirdPerson,
        modded = modded,
        modIds = modIds,
        modNames = modNames,
        country = country,
        isFavorite = isFavorite,
        isExpanded = false
    )
}

data class DayzServerUi(
    private val id: Long = -1,
    private val name: String = "",
    private val ip: String = "",
    private val port: Int = 0,
    private val players: Int = 0,
    private val maxPlayers: Int = 0,
    private val rank: Int = 0,
    private val status: String = "",
    private val version: String = "",
    private val password: Boolean = false,
    private val official: Boolean = false,
    private val time: String = "",
    private val thirdPerson: Boolean = false,
    private val modded: Boolean = false,
    private val modIds: List<Long> = emptyList(),
    private val modNames: List<String> = emptyList(),
    private val country: String = "",
    private var isFavorite: Boolean = false,
    private var isExpanded: Boolean = false
)
