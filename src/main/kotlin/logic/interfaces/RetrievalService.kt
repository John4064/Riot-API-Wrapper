package logic.interfaces

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import models.MatchDto

interface RetrievalService {
    val mongoClient : MongoClient
    val mongodbUri: String
    var leagueDB : MongoDatabase


//    fun getMatchData(matchId: String) : MatchDto?
    fun getMatchData(matchId: String)

    fun gatherMatchIds()

}