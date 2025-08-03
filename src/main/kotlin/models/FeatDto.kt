package models

import kotlinx.serialization.Serializable

@Serializable
data class FeatDto(
    val featState: Int?=0
)
