package logic.interfaces

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import models.AccountDto
import models.MatchDto
import models.SummonerDto

interface IngestionLogic {

    val mongoClient : MongoClient

    val mongodbUri: String

    val summonerDatabase : MongoDatabase

    fun insertSummonerData(summonerData: SummonerDto)
    fun insertAccountData(accountData: AccountDto)
    fun insertMatchData(matchData: MatchDto)
}