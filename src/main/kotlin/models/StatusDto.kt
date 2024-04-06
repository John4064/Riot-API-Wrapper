package models

import kotlinx.serialization.Serializable

@Serializable
data class StatusDto(
    val message: String?="",
    val status_code: Int
)
