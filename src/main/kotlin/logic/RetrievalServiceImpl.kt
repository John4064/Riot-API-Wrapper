package logic

import com.mongodb.MongoWriteException
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import config.AppConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import logic.interfaces.RetrievalService
import models.MatchDto
import org.bson.Document

class RetrievalServiceImpl : RetrievalService {
    private lateinit var config: AppConfig
    override var mongodbUri : String
    override lateinit var mongoClient : MongoClient
    override lateinit var leagueDB : MongoDatabase

    private val logger = KotlinLogging.logger {}

    init {
        config = AppConfig()
        config.loadFromFile("application.properties")
        mongodbUri = config.mongodbUri
        mongoClient = MongoClient.create(mongodbUri)
        leagueDB= mongoClient.getDatabase("league-stats")
        logger.info { "Retrieval logic is initialized." }
    }

    override fun getMatchData(matchId: String) {
        val collection = leagueDB.getCollection<MatchDto>("Matches24")
        runBlocking {
            try{
                val query= Document("","")
                val result = collection.find<MatchDto>(query).limit(1).collect()
                val data = result to MatchDto
                logger.info { data.first }
//                return@runBlocking null
//                collection.insertOne(matchData)
            }catch(e: MongoWriteException) {
                logger.info { "Match exists already at id:  ${matchId}" }
//                return@runBlocking null
            }catch(e: Exception){
                logger.error { "Error occurred inserting match: ${matchId}" }
//                return@runBlocking null
            }
        }
    }


}