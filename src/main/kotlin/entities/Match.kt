package entities

import org.jetbrains.exposed.v1.core.Table

object Match : Table("Match") {
    val matchId = long("match_id").autoIncrement().primaryKey()
    val infoId = long("info_id").references(Info.infoId)
    val metadataId = long("metadata_id").references(Metadata.metadataId)
}