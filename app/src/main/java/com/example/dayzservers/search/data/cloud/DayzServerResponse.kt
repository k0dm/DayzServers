package com.example.dayzservers.search.data.cloud

import com.google.gson.annotations.SerializedName

data class DayZServersResponse(
    @SerializedName("data") private val data: List<DayzServerData>,
    @SerializedName("links") private val links: ServerLinks,
) {
    fun toListDayzServerCloud(): List<DayzServerCloud> = data.map {
        DayzServerCloud(
            id = it.id,
            name = it.attributes.name,
            ip = it.attributes.ip,
            port = it.attributes.port,
            players = it.attributes.players,
            maxPlayers = it.attributes.maxPlayers,
            rank = it.attributes.rank,
            status = it.attributes.status,
            version = it.attributes.details.version,
            password = it.attributes.details.password,
            official = it.attributes.details.official,
            time = it.attributes.details.time,
            thirdPerson = it.attributes.details.thirdPerson,
            modded = it.attributes.details.modded,
            modIds = it.attributes.details.modIds,
            modNames = it.attributes.details.modNames,
            country = it.attributes.country,
        )
    }
}

data class DayzServerData(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: Long,
    @SerializedName("attributes") val attributes: DayZServerAttributes,
)

data class DayZServerAttributes(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("ip") val ip: String,
    @SerializedName("port") val port: Int,
    @SerializedName("players") val players: Int,
    @SerializedName("maxPlayers") val maxPlayers: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("status") val status: String,
    @SerializedName("details") val details: DayzServerDetails,
    @SerializedName("portQuery") val portQuery: Int,
    @SerializedName("country") val country: String,
    @SerializedName("queryStatus") val queryStatus: String,
)

data class DayzServerDetails(
    @SerializedName("version") val version: String,
    @SerializedName("password") val password: Boolean,
    @SerializedName("official") val official: Boolean,
    @SerializedName("time") val time: String,
    @SerializedName("thirdPerson") val thirdPerson: Boolean,
    @SerializedName("modded") val modded: Boolean,
    @SerializedName("modIds") val modIds: List<Long>,
    @SerializedName("modNames") val modNames: List<String>,
)

data class ServerLinks(
    @SerializedName("prev") val prev: String?,
    @SerializedName("next") val next: String?,
)