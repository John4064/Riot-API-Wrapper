package logic.interfaces

import models.AccountDto
import models.MatchDto
import okhttp3.OkHttpClient

interface AggregationLogic {

    val httpClient: OkHttpClient

    val apiKey: String

    fun getAccountData(userName:String, tagLine: String): AccountDto?

    fun getMatchIDs(userName: String, tagLine: String, matchCount: Int): ArrayList<String>

    fun getMatchData(matchID: String) :MatchDto?
}