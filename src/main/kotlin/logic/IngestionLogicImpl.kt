package logic

import com.mongodb.MongoWriteException
import config.AppConfig
import config.connectToDatabase
import kotlinx.coroutines.runBlocking
import logic.interfaces.IngestionLogic
import models.SummonerDto
import io.github.oshai.kotlinlogging.KotlinLogging
import models.AccountDto
import models.InfoDto
import models.MatchDto
import models.MetadataDto
import repo.InfoRepo
import repo.MatchRepo
import repo.MetadataRepo
import repo.ParticipantRepo
import repo.UserMatchRepo

class IngestionLogicImpl : IngestionLogic {

    private lateinit var config: AppConfig
    private lateinit var matchRepo: MatchRepo
    private lateinit var infoRepo: InfoRepo
    private lateinit var metadataRepo: MetadataRepo
    private lateinit var participantRepo: ParticipantRepo
    private lateinit var userMatchRepo: UserMatchRepo

    private val logger = KotlinLogging.logger {}

    init {
        config = AppConfig()
        config.loadFromFile("application.properties")
        logger.info { "Ingestion logic is initialized." }
        matchRepo = MatchRepo()
        infoRepo = InfoRepo()
        metadataRepo = MetadataRepo()
        participantRepo = ParticipantRepo()
        userMatchRepo = UserMatchRepo()
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

    override fun insertMatchData(matchData: MatchDto, username: String,tagline: String){
        runBlocking {
            try{
                val infoId: Long=insertInfoData(matchData.info)
                val metadataId: Long=insertMetadataData(matchData.metadata)
                val generatedMatchId=matchRepo.insertMatch(matchData,infoId,metadataId)
                insertUserMatchData(matchData.metadata.matchId,username,tagline)
                logger.info { "Inserted Match ${matchData.info.gameId}" }
            }catch(e: Exception){
                logger.error { "Error occurred inserting match: ${matchData.info.gameId}" }
                logger.error { e.toString() }
            }
        }
    }

    override fun insertUserMatchData(matchId: String, username: String, tagline: String){
        runBlocking {
            try{
                userMatchRepo.insertUserMatch(matchId,username,tagline)
                logger.info { "Inserted User Match ${"$username#$tagline"}" }
            }catch(e: Exception){
                logger.error { "User Error occurred inserting user match: ${username+tagline}" }
                logger.error { e.toString() }
            }
        }
    }


    override fun insertInfoData(infoData: InfoDto): Long {
        var generateId=0L
            runBlocking {
                try{
                    generateId=infoRepo.insertInfo(infoData)
                    return@runBlocking generateId
                }catch (e: Exception){
                    logger.error { "Error occurred inserting info: ${infoData.gameId}" }
                    logger.error { e.toString() }
                }
            }
            return generateId
        }

    override fun insertMetadataData(metaData: MetadataDto): Long {
        var generateId=0L
        runBlocking {
            try{
                generateId=metadataRepo.insertMetadata(metaData)
                for(participant in metaData.participants){
                    participantRepo.insertParticipant(generateId, participant)
                }
                return@runBlocking generateId
            }catch (e: Exception){
                logger.error { "Error occurred inserting metaData: ${metaData.matchId}" }
                logger.error { e.toString() }
            }
        }
        return generateId
    }
}