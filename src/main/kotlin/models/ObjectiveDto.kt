package models

import kotlinx.serialization.Serializable

@Serializable
data class ObjectiveDto(
    val first: Boolean,
    val kills: Int
)
