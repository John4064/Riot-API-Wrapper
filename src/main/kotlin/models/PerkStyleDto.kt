package models

import kotlinx.serialization.Serializable

@Serializable
data class PerkStyleDto(
    val description: String,
    val selections: ArrayList<PerkStyleSelectionDto>,
    val style: Int
)
