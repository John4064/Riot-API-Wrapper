package models

import kotlinx.serialization.Serializable

@Serializable
data class BanDto (
    val championId: Int,
    val pickTurn: Int
)