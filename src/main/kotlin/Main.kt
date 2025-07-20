import io.github.oshai.kotlinlogging.KotlinLogging
import logic.AggregationLogicImpl
import logic.RiotLogicImpl
import logic.IngestionLogicImpl
import logic.RetrievalServiceImpl
import logic.interfaces.AggregationLogic
import logic.interfaces.RiotLogic
import logic.interfaces.IngestionLogic
import logic.interfaces.RetrievalService
import models.MatchDto
import models.SummonerDto
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    //Todo: After storing the data in the DB have checks made for existing data to reduce # of api calls for puuid and summonerID
    val aggLogic: AggregationLogic = AggregationLogicImpl()
    val ingLogic: IngestionLogic = IngestionLogicImpl()
    val retrLogic: RetrievalService = RetrievalServiceImpl()
    try{
//        logger.info { "${matches.size} Matches to insert" }
//        for(match in matches){
//            ingLogic.insertMatchData(match)
//        }
        val matches: ArrayList<MatchDto> = aggLogic.gatherRankedGames("Tidal","RCS",1)
        logger.info { matches.toString() }
    }catch(e: Exception) {
        logger.error { "ERROR" }
        logger.error { e.toString() }
    }
}