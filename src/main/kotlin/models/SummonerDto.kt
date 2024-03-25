package models

import kotlinx.serialization.Serializable

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
