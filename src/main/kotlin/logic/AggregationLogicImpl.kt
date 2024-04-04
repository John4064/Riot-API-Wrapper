package logic

import config.AppConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.AggregationLogic
import logic.interfaces.RiotLogic
import models.AccountDto
import models.MatchDto
import okhttp3.OkHttpClient

//Prebuilt functions like given a list of matches gather all linked accounts
class AggregationLogicImpl : AggregationLogic {

    override var apiKey: String
    override val httpClient: OkHttpClient
        get() = OkHttpClient();
    private val logger = KotlinLogging.logger {}
    private lateinit var riotLogic: RiotLogic
    init {
        val config = AppConfig()
        config.loadFromFile("application.properties")
        apiKey= config.apiKey
        riotLogic = RiotLogicImpl()
    }

    override fun gatherPlayedWithAccounts(matches: ArrayList<MatchDto>){
        TODO("Check all match data for a user and generate a list of users names")
    }

    override fun gatherRankedGames(username: String, tagline: String) :ArrayList<MatchDto> {
        val matches: ArrayList<MatchDto> = arrayListOf<MatchDto>()
        //qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg
        

        return matches
    }

}