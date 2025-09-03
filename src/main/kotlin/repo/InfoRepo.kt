package repo

import entities.Info
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.encodeToJsonElement
import models.InfoDto
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertReturning
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class InfoRepo {
    fun insertInfo(infoData: InfoDto): Long =transaction{
        val generatedId=Info.insertReturning { row->
            row[endOfGameResult] = infoData.endOfGameResult
            row[gameCreation] = infoData.gameCreation
            row[gameDuration] = infoData.gameDuration
            row[gameEndTimestamp] = infoData.gameEndTimestamp
            row[gameId] = infoData.gameId
            row[gameMode] = infoData.gameMode
            row[gameName] = infoData.gameName
            row[gameStartTimestamp] = infoData.gameStartTimestamp
            row[gameType] = infoData.gameType
            row[gameVersion] = infoData.gameVersion
            row[mapId] = infoData.mapId
            row[participants] = infoData.participants
            row[platformId] = infoData.platformId
            row[queueId] = infoData.queueId
            row[teams] = infoData.teams
            row[tournamentCode] = infoData.tournamentCode
        }.single()[Info.infoId]
        return@transaction generatedId
    }
}