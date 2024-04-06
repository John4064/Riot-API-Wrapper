package logic

import config.AppConfig
import kotlinx.serialization.json.Json
import logic.interfaces.RiotLogic
import models.AccountDto
import models.MatchDto
import models.SummonerDto
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import io.github.oshai.kotlinlogging.KotlinLogging

class RiotLogicImpl : RiotLogic{
    override var apiKey: String
    override val httpClient: OkHttpClient
        get() = OkHttpClient();
    private val logger = KotlinLogging.logger {}

    //Todo: convert to constant file
    private val thisYearEpochTimeStamp: Long=1704067200

    init {
        val config = AppConfig()
        config.loadFromFile("application.properties")
        apiKey= config.apiKey
    }

    //1 api call
    override fun getAccountData(userName:String, tagLine: String): AccountDto? {
        try{
            val request = Request.Builder()
                .url("https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/$userName/$tagLine")
                .get()
                .addHeader("X-Riot-Token", apiKey)
                .build();
            val response = httpClient.newCall(request).execute();
            val jsonString: String = response.body.string()
            return Json.decodeFromString<AccountDto>(jsonString)
        }catch(e: Exception){
            logger.error { "Error Occured gathering Account Data with username: $userName" }
            logger.error { e.toString() }
            return null
        }
    }
    //1 api call
    override fun getSummonerDataByPuuid(puuid:String): SummonerDto? {
        try{
            val request = Request.Builder()
                .url("https://americas.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/$puuid")
                .get()
                .addHeader("X-Riot-Token", apiKey)
                .build();
            val response = httpClient.newCall(request).execute();
            val jsonString: String = response.body.string()
            return Json.decodeFromString<SummonerDto>(jsonString)
        }catch(e: Exception){
            logger.error("Error Occurred gathering Summoner Data with puuid: $puuid")
            logger.error(e.toString())
            return null
        }
    }

    //2 api call
    override fun getSummonerDataByUsername(userName: String, tagLine: String): SummonerDto?{
        return try{
            val accountData: AccountDto? = getAccountData(userName,tagLine)
            if(accountData!= null){
                val summonerData: SummonerDto? = getSummonerDataByPuuid(accountData.puuid)
                summonerData
            }else{
                null
            }
        }catch(e: Exception){
            logger.error("Error Occurred gathering Account Data with username: $userName")
            logger.error(e.toString())
            null
        }
    }

    //2 api calls
    override fun getMatchIDs(userName: String, tagLine: String, matchCount: Int, startCount: Int): ArrayList<String> {
        val accountData = getAccountData(userName,tagLine)
        val puuid = accountData?.puuid
        val matchType: String = "ranked"
//        val matchCountStr = matchCount.toString()
        if(accountData == null){
            return ArrayList()
        }
        //startTime=$thisYearEpochTimeStamp
        try{
            val request2 = Request.Builder()
                .url("https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?type=$matchType&start=$startCount&count=$matchCount")
                .get()
                .addHeader("X-Riot-Token", apiKey)
                .build();
            val matchIdResponse = httpClient.newCall(request2).execute();

            val matchList: String = matchIdResponse.body.string().replace("\"","")
            val elements = matchList.substring(1, matchList.length - 1).split(",")
            // Create an ArrayList from the elements
            return ArrayList<String>(elements)
        }catch(e: Exception) {
            logger.error { "Error Occurred gathering Match IDs" }
            logger.error { e.toString() }
            return ArrayList()
        }

    }

    //1 api calls
    override fun getMatchData(matchID: String) : MatchDto?{
        return try {
            val request = Request.Builder()
                .url("https://americas.api.riotgames.com/lol/match/v5/matches/%s".format(matchID))
                .get()
                .addHeader("X-Riot-Token", apiKey)
                .build();
            val response = httpClient.newCall(request).execute();//The Match Data Itself
            val jsonStringData: String = response.body.string().replace("12AssistStreakCount","assistStreakCount").replace("playerScore","PlayerScore")
            Json.decodeFromString<MatchDto>(jsonStringData)
        }catch (e : Exception){
            logger.error { "Error Occured gathering Match Data with matchID: $matchID" }
            logger.error { e.toString() }
            return null
        }
    }
}