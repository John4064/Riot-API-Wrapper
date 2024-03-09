package models

import kotlinx.serialization.Serializable

@Serializable
data class PerkStyleSelectionDto(
    val perk: Int,
    val var1: Int,
    val var2: Int,
    val var3: Int
)
