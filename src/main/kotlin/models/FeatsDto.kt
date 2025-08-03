package models

import kotlinx.serialization.Serializable

@Serializable
data class FeatsDto(
    val EPIC_MONSTER_KILL: FeatDto?=null,
    val FIRST_BLOOD:  FeatDto?=null,
    val FIRST_TURRET:  FeatDto?=null,
)
