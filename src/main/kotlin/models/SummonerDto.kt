package models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class SummonerDto(
    val accountId: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val name: String,
    val id: String,
    val puuid: String,
    val summonerLevel: Long
)
