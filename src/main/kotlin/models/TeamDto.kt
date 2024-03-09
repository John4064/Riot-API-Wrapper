package models

import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    val bans: ArrayList<BanDto>,
    val objectives: ObjectivesDto,
    val teamId: Int,
    val win: Boolean
)
