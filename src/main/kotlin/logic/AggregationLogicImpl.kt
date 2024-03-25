package logic

import config.AppConfig
import kotlinx.serialization.json.Json
import logic.interfaces.AggregationLogic
import models.AccountDto
import models.MatchDto
import models.SummonerDto
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import org.apache.logging.log4j.kotlin.Logging
class AggregationLogicImpl : AggregationLogic,Logging{
    override var apiKey: String
    override val httpClient: OkHttpClient
        get() = OkHttpClient();

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
            logger.error("Error Occured gathering Account Data with username: $userName")
            logger.error(e.toString())
            return null
        }
    }
    //1 api call
    override fun getSummonerData(userName:String, tagLine: String): SummonerDto? {
        try{
            val request = Request.Builder()
                .url("https://americas.api.riotgames.com/riot/account/v4/summoners/by-riot-id/$userName/$tagLine")
                .get()
                .addHeader("X-Riot-Token", apiKey)
                .build();
            val response = httpClient.newCall(request).execute();
            val jsonString: String = response.body.string()
            return Json.decodeFromString<AccountDto>(jsonString)
        }catch(e: Exception){
            logger.error("Error Occured gathering Account Data with username: $userName")
            logger.error(e.toString())
            return null
        }
    }


    //2 api calls
    override fun getMatchIDs(userName: String, tagLine: String, matchCount: Int): ArrayList<String> {
        val accountDto = getAccountData(userName,tagLine)
        val puuid = accountDto?.puuid
        val matchCountStr = matchCount.toString()
        if(accountDto == null){
            return ArrayList()
        }

        val request2 = Request.Builder()
            .url("https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=0&count=$matchCount")
            .get()
            .addHeader("X-Riot-Token", apiKey)
            .build();
        val matchIdResponse = httpClient.newCall(request2).execute();

        val matchList: String = matchIdResponse.body.string().replace("\"","")
        val elements = matchList.substring(1, matchList.length - 1).split(",")
        // Create an ArrayList from the elements
        return ArrayList<String>(elements)
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
            val jsonStringData: String = response.body.string().replace("12AssistStreakCount","assistStreakCount")
            Json.decodeFromString<MatchDto>(jsonStringData)
        }catch (e : Exception){
            logger.error("Error Occured gathering Match Data with matchID: $matchID")
            logger.error(e.toString())
            return null
        }
    }
}