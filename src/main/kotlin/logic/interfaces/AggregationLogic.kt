package logic.interfaces

import models.MatchDto
import okhttp3.OkHttpClient

interface AggregationLogic {

    var apiKey: String
    val httpClient: OkHttpClient
    //Fetch all Summoners from Matches
    fun gatherPlayedWithAccounts(matches: ArrayList<MatchDto>)
    //future parameters could be queue type,
    fun gatherRankedGames(username: String, tagline: String, matchCount: Int):ArrayList<MatchDto>
    //Fetch Accounts from matches
}