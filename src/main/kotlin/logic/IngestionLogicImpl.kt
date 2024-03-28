package logic

import com.mongodb.MongoWriteException
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import config.AppConfig
import kotlinx.coroutines.runBlocking
import logic.interfaces.IngestionLogic
import models.SummonerDto
import org.apache.logging.log4j.kotlin.Logging

class IngestionLogicImpl : IngestionLogic, Logging {

    override val mongoClient : MongoClient
    override val mongodbUri : String
    override val database : MongoDatabase

    init {
        val config = AppConfig()
        config.loadFromFile("application.properties")
        mongodbUri= config.mongodbUri
        mongoClient  =MongoClient.create(mongodbUri)
        database = mongoClient.getDatabase("league-stats")
    }


    override fun insertSummonerData(summonerData: SummonerDto){
        val collection = database.getCollection<SummonerDto>("Summoners")
        runBlocking {
            try{
                collection.insertOne(summonerData)
            }catch(e: MongoWriteException) {
                logger.info("User already exists:  ${summonerData.name}" )
            }catch(e: Exception){
                logger.error("Error occured inserting summoner: ${summonerData.name}")
            }
        }
    }
}