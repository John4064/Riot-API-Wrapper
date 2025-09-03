package entities

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

object Participant : Table("metadata_participant") {
    val metadataId = long("metadata_id")
    override val primaryKey = PrimaryKey(metadataId)
    val participantIdentifier = varchar("participant_identifier", 255)
    val createDttm = datetime("create_dttm")
}