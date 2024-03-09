package models

import kotlinx.serialization.Serializable

@Serializable
data class MatchDto(
    val info: InfoDto,
    val metadata: MetadataDto
)
