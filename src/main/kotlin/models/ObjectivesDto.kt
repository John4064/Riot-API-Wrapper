package models

import kotlinx.serialization.Serializable

@Serializable
data class ObjectivesDto(
    val atakhan: ObjectiveDto?=null,
    val baron: ObjectiveDto?=null,
    val champion: ObjectiveDto?=null,
    val dragon: ObjectiveDto?=null,
    val horde: ObjectiveDto?=null,
    val inhibitor: ObjectiveDto?=null,
    val riftHerald: ObjectiveDto?=null,
    val tower: ObjectiveDto?=null

)
