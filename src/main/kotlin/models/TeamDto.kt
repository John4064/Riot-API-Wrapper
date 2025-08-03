package models

import kotlinx.serialization.Serializable


@Serializable
data class TeamDto(
    val bans: ArrayList<BanDto>,
    val feats: FeatsDto?=null,
    val objectives: ObjectivesDto,
    val teamId: Int,
    val win: Boolean
)
