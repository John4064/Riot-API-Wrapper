package entities

import org.jetbrains.exposed.v1.core.Table

object Match : Table("match") {
    val matchId = long("match_id").autoIncrement()
    val infoId = long("info_id").references(Info.infoId)
    val metadataId = long("metadata_id").references(Metadata.metadataId)
    val riotMatchId = varchar("riot_match_id",255)
    override val primaryKey = PrimaryKey(riotMatchId)

}