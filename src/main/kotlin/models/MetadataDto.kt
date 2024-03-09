package models

import kotlinx.serialization.Serializable

@Serializable
data class MetadataDto(
    val dataVersion: String,
    val matchId: String,
    val participants: ArrayList<String>
)
