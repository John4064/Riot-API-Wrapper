package logic

import config.AppConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.AggregationLogic
import logic.interfaces.RetrievalService
import logic.interfaces.RiotLogic
import models.AccountDto
import models.MatchDto
import okhttp3.OkHttpClient
import java.lang.Exception

//Prebuilt functions like given a list of matches gather all linked accounts
class AggregationLogicImpl : AggregationLogic {

    override var apiKey: String
    override val httpClient: OkHttpClient
        get() = OkHttpClient();
    private val logger = KotlinLogging.logger {}
    private lateinit var riotLogic: RiotLogic
    private lateinit var retrievalLogic:  RetrievalService
    init {
        val config = AppConfig()
        config.loadFromFile("application.properties")
        apiKey= config.apiKey
        riotLogic = RiotLogicImpl()
        retrievalLogic = RetrievalServiceImpl()
    }

    override fun gatherPlayedWithAccounts(matches: ArrayList<MatchDto>){
        TODO("Check all match data for a user and generate a list of users names")
    }

    override fun gatherRankedGames(username: String, tagline: String, matchCount: Int) :ArrayList<MatchDto> {
        //qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg
        val idLists : ArrayList<String> = riotLogic.getMatchIDs(username,tagline,matchCount,0)

        if (idLists.isNotEmpty()){
                //Ping our Documents to find exisitng matches
                //could grab list of al my ids to verify we dont make additional calls
                val matches: ArrayList<MatchDto> = arrayListOf<MatchDto>()
                for (matchId in idLists){
                    try{
                        retrievalLogic.getMatchData(matchId)
                        logger.info { "Gathering match data on ${matchId}" }
                        val tempMatchData: MatchDto? =riotLogic.getMatchData(matchId)
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