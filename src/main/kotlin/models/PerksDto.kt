package models

import kotlinx.serialization.Serializable

@Serializable
data class PerksDto(
    val statPerks: PerkStatsDto,
    val styles: ArrayList<PerkStyleDto>
)
