package logic

import com.mongodb.MongoWriteException
import config.AppConfig
import config.connectToDatabase
import kotlinx.coroutines.runBlocking
import logic.interfaces.IngestionLogic
import models.SummonerDto
import io.github.oshai.kotlinlogging.KotlinLogging
import models.AccountDto
import models.MatchDto

class IngestionLogicImpl : IngestionLogic {

    private lateinit var config: AppConfig

    private val logger = KotlinLogging.logger {}

    init {
        config = AppConfig()
        config.loadFromFile("application.properties")
        connectToDatabase()
        logger.info { "Ingestion logic is initialized." }
    }

    override fun insertSummonerData(summonerData: SummonerDto){
//        val collection = leagueDB.getCollection<SummonerDto>("Summoners")
        runBlocking {
            try{
//                collection.insertOne(summonerData)
                logger.info { "Inserted Summoner data ${summonerData.name}" }
            }catch(e: MongoWriteException) {
                logger.info { "User already exists:  ${summonerData.name}" }
            }catch(e: Exception){
                logger.error { "Error occured inserting summoner: ${summonerData.name}" }
            }
        }
    }

    override fun insertAccountData(accountData: AccountDto){
//        val collection = leagueDB.getCollection<AccountDto>("Accounts")
        runBlocking {
            try{
//                collection.insertOne(accountData)
                logger.info { "Inserted Account data ${accountData.gameName}" }
            }catch(e: MongoWriteException) {
                logger.info { "User already exists:  ${accountData.gameName}" }
            }catch(e: Exception){
                logger.error { "Error occured inserting summoner: ${accountData.gameName}" }
            }
        }
    }

    override fun insertMatchData(matchData: MatchDto){
        logger.info { "ABC" }
        runBlocking {
            try{
                logger.info { "Inserted Match ${matchData.info.gameId}" }
            }catch(e: MongoWriteException) {
                logger.info { "Match exists already at id:  ${matchData.info.gameId}" }
            }catch(e: Exception){
                logger.error { "Error occurred inserting match: ${matchData.info.gameId}" }
            }
        }
    }
}