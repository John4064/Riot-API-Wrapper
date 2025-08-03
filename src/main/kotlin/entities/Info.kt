package entities

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.json.jsonb

object Info : Table("Info") {
    val infoId = long("info_id").autoIncrement().primaryKey()
    val endOfGameResult = varchar("end_of_game_result", length = 255).nullable()
    val gameCreation = long("game_creation").nullable()
    val gameDuration = long("game_duration").nullable()
    val gameEndTimestamp = long("game_end_timestamp").nullable()
    val gameId = long("game_id").nullable()
    val gameMode = varchar("game_mode", 255).nullable()
    val gameName = varchar("game_name", 255).nullable()
    val gameStartTimestamp = long("game_start_timestamp").nullable()
    val gameType = varchar("game_type", 255).nullable()
    val gameVersion = varchar("game_version", 255).nullable()
    val mapId = integer("map_id").nullable()
    val participants = jsonb("participants", org.jetbrains.exposed.v1.json.Json.Default).nullable(false) // non-null JSONB
    val platformId = varchar("platform_id", 255).nullable()
    val queueId = integer("queue_id").nullable()
    val teams = jsonb("teams", org.jetbrains.exposed.v1.json.Json.Default).nullable(false) // non-null JSONB
    val tournamentCode = varchar("tournament_code", 255).nullable()
}