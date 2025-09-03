package entities

import org.jetbrains.exposed.v1.core.Table

object UserMatch : Table("user_match") {
    val userId = varchar("user_id",255)
    val matchId = varchar("match_id",255).references(Match.riotMatchId)
}