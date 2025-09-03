package repo

import entities.Match
import models.MatchDto
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class MatchRepo {

    fun insertMatch(matchData: MatchDto, newInfoId: Long, newMetadataId: Long) =transaction{
        val generatedId=Match.insertReturning { row ->
            row[infoId] = newInfoId
            row[metadataId] = newMetadataId
            row[riotMatchId] = matchData.metadata.matchId
        }.single()[Match.matchId]
        return@transaction generatedId
    }
    fun checkMatchExist(matchId: String): Boolean= transaction {
        val exists = Match.select(Match.riotMatchId eq matchId).where(Match.riotMatchId eq matchId).count()
        return@transaction exists.toInt() !=0
    }


}