import config.connectToDatabase
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.serialization.json.Json
import logic.AggregationLogicImpl
import logic.IngestionLogicImpl
import logic.RetrievalServiceImpl
import logic.interfaces.AggregationLogic
import logic.interfaces.IngestionLogic
import logic.interfaces.RetrievalService
import models.MatchDto
import java.lang.Exception
import kotlin.collections.ArrayList

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    connectToDatabase()

    //Todo: After storing the data in the DB have checks made for existing data to reduce # of api calls for puuid and summonerID
    val aggLogic: AggregationLogic = AggregationLogicImpl()
    val ingLogic: IngestionLogic = IngestionLogicImpl()
    val retrLogic: RetrievalService = RetrievalServiceImpl()
    val username ="PolyGone"
    val tagline="9622"
    try{
        val matches: ArrayList<MatchDto> = aggLogic.gatherRankedGames(username,tagline,25)
        for (match in matches){
            ingLogic.insertMatchData(match,username,tagline)
        }
    }catch(e: Exception) {
        logger.error { "Generic Catch All Error" }
        logger.error { e.toString() }
        e.printStackTrace()

    }
}