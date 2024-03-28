package logic.interfaces

import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import models.SummonerDto

interface IngestionLogic {

    val mongoClient : MongoClient

    val mongodbUri: String

    val database : MongoDatabase

    fun insertSummonerData(summonerData: SummonerDto)
}