package models

import kotlinx.serialization.Serializable

@Serializable
data class PerkStatsDto(
    val defense: Int,
    val flex: Int,
    val offense: Int
)
