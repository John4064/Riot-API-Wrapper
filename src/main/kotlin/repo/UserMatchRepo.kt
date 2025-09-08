package repo

import entities.UserMatch
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq

import org.jetbrains.exposed.v1.jdbc.andWhere
import org.jetbrains.exposed.v1.jdbc.insertIgnore
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.jetbrains.exposed.v1.jdbc.upsert
import kotlin.and

private val logger = KotlinLogging.logger {}

class UserMatchRepo {

    fun insertUserMatch(newMatchId: String,username: String, tagline: String) =transaction{
        //Perhaps add to UserMatch table a column for game date
            UserMatch.insertIgnore{ row ->
                row[userId] = "$username#$tagline"
                row[matchId] = newMatchId
            }
    }
}