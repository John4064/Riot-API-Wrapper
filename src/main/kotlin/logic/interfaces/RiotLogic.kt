package logic.interfaces

import models.AccountDto
import models.MatchDto
import models.SummonerDto
import okhttp3.OkHttpClient

interface RiotLogic {

    val httpClient: OkHttpClient

    val apiKey: String

    fun getAccountData(userName: String, tagLine: String): AccountDto?

    fun getSummonerDataByPuuid(puuid: String): SummonerDto?

    fun getSummonerDataByUsername(userName: String, tagLine: String): SummonerDto?

    //Todo: add parameters(startTime, endTime, queue, type, start, count)
    fun getMatchIDs(userName: String, tagLine: String, matchCount: Int, startCount: Int): ArrayList<String>

    fun getMatchData(matchID: String) :MatchDto?
}