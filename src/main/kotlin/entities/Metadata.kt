package entities

import entities.Info.infoId
import org.jetbrains.exposed.v1.core.Table

object Metadata : Table("metadata") {
    val metadataId = long("metadata_id").autoIncrement()
    override val primaryKey = PrimaryKey(metadataId)
    val dataVersion = varchar("data_version", 255)
    val matchId = varchar("match_id", 255)
}