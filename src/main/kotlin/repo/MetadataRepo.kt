package repo

import entities.Info.databaseGenerated
import entities.Info.long
import entities.Metadata
import models.MetadataDto
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class MetadataRepo {

    fun insertMetadata(metadataData: MetadataDto): Long =transaction{
        val generatedId=Metadata.insertReturning { row ->
            row[matchId]= metadataData.matchId
            row[dataVersion] = metadataData.dataVersion
        }.single()[Metadata.metadataId]
        return@transaction generatedId
    }
}