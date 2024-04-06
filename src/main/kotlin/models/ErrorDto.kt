package models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDto(
    val status: StatusDto?=null
)
