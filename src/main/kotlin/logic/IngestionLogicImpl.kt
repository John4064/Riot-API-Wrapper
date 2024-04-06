package logic

import com.mongodb.MongoWriteException
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import config.AppConfig
import kotlinx.coroutines.runBlocking
import logic.interfaces.IngestionLogic
import models.SummonerDto
import io.github.oshai.kotlinlogging.KotlinLogging
import models.AccountDto
import models.MatchDto

class IngestionLogicImpl : IngestionLogic {

    private lateinit var config: AppConfig
    override var mongodbUri : String
    override lateinit var mongoClient : MongoClient
    override lateinit var summonerDatabase : MongoDatabase

    private val logger = KotlinLogging.logger {}

    init {
        val config: AppConfig = AppConfig()
        config.loadFromFile("application.properties")
        mongodbUri=config.mongodbUri
        mongoClient = MongoClient.create(mongodbUri)
        summonerDatabase= mongoClient.getDatabase("league-stats")
        logger.info { "Ingestion logic is initialized." }
    }

    override fun insertSummonerData(summonerData: SummonerDto){
        val collection = summonerDatabase.getCollection<SummonerDto>("Summoners")
        runBlocking {
            try{
                collection.insertOne(summonerData)
            }catch(e: MongoWriteException) {
                logger.info { "User already exists:  ${summonerData.name}" }
            }catch(e: Exception){
                logger.error { "Error occured inserting summoner: ${summonerData.name}" }
            }
        }
    }

    override fun insertAccountData(accountData: AccountDto){
        val collection = summonerDatabase.getCollection<AccountDto>("Accounts")
        runBlocking {
            try{
                collection.insertOne(accountData)
            }catch(e: MongoWriteException) {
                logger.info { "User already exists:  ${accountData.gameName}" }
            }catch(e: Exception){
                logger.error { "Error occured inserting summoner: ${accountData.gameName}" }
            }
        }
    }

    override fun insertMatchData(matchData: MatchDto){
        val collection = summonerDatabase.getCollection<MatchDto>("Matches24")
        runBlocking {
            try{
                collection.insertOne(matchData)
            }catch(e: MongoWriteException) {
                logger.info { "Match exists already at id:  ${matchData.info.gameId}" }
            }catch(e: Exception){
                logger.error { "Error occurred inserting match: ${matchData.info.gameId}" }
            }
        }
    }
}