package logic

import config.AppConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.AggregationLogic
import logic.interfaces.IngestionLogic
import logic.interfaces.RetrievalService
import logic.interfaces.RiotLogic
import models.MatchDto
import java.lang.Exception
import okhttp3.OkHttpClient
import kotlin.math.log

//Prebuilt functions like given a list of matches gather all linked accounts
class AggregationLogicImpl : AggregationLogic {

    override var apiKey: String
    override val httpClient: OkHttpClient
        get() = OkHttpClient();
    private val logger = KotlinLogging.logger {}
    private lateinit var riotLogic: RiotLogic
    private lateinit var retrievalLogic:  RetrievalService
    private lateinit var ingestionLogic: IngestionLogic

    init {
        val config = AppConfig()
        config.loadFromFile("application.properties")
        apiKey= config.apiKey
        riotLogic = RiotLogicImpl()
        retrievalLogic = RetrievalServiceImpl()
        ingestionLogic = IngestionLogicImpl()
    }

    override fun gatherPlayedWithAccounts(matches: ArrayList<MatchDto>){
        TODO("Check all match data for a user and generate a list of users names")
    }

    override fun gatherRankedGames(username: String, tagline: String, matchCount: Int) :ArrayList<MatchDto> {
        var idLists : ArrayList<String> = riotLogic.getMatchIDs(username,tagline,matchCount,0)
        var tempList: ArrayList<String> = arrayListOf()
        if (idLists.isNotEmpty()){
            logger.info { idLists.toString() }
            //Remove ranked matches
            for(matchId in idLists){
                try{
                    if(retrievalLogic.checkMatchExist(matchId)){
                        logger.info { "Match $matchId already exists!" }
                        ingestionLogic.insertUserMatchData(matchId,username,tagline)//check if this usermatch exists too
                        tempList.add(matchId)
                    }
                }catch(e: Exception){
                    logger.error { "Error Occurred checking if Match Exists $matchId" }
                    logger.error { e.toString() }
                    continue
                }

            }
            idLists.removeAll(tempList.toSet())
            logger.info { "POST REMOVAL: $idLists" }
            val matches: ArrayList<MatchDto> = arrayListOf<MatchDto>()
            for (matchId in idLists){
                try{
                    logger.info { "Gathering match data on ${matchId}" }
                    val tempMatchData: MatchDto? =riotLogic .getMatchData(matchId)
                    if (tempMatchData != null) {
                        matches.add(tempMatchData)
                    }
                }catch(e: Exception) {
                    logger.error { "Error Occurred gathering Match IDs" }
                    logger.error { e.toString() }
                    continue
                }
            }
            return matches

        }else{
            return ArrayList()
        }
    }
}