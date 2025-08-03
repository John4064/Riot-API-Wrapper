package logic

import config.AppConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.RetrievalService

class RetrievalServiceImpl : RetrievalService {
    private lateinit var config: AppConfig

    private val logger = KotlinLogging.logger {}

    init {

        config = AppConfig()
        config.loadFromFile("application.properties")
        //Rewrite mongo logic
        logger.info { "Retrieval logic is initialized." }
    }

//    override fun getMatchData(matchId: String) {
//        val collection = leagueDB.getCollection<MatchDto>("Matches24")
//        runBlocking {
//            try{
//                val query= Document("","")
//                val result = collection.find<MatchDto>(query).limit(1).collect()
//                val data = result to MatchDto
//                logger.info { data.first }
////                return@runBlocking null
////                collection.insertOne(matchData)
//            }catch(e: MongoWriteException) {
//                logger.info { "Match exists already at id:  ${matchId}" }
////                return@runBlocking null
//            }catch(e: Exception){
//                logger.error { "Error occurred inserting match: ${matchId}" }
////                return@runBlocking null
//            }
//        }
//    }
//
//    override fun gatherMatchIds() {
//        val collection = leagueDB.getCollection<MatchDto>("Matches24")
//        runBlocking {
//            try{
//                val query= Document("","")
//                val result = collection.distinct<MatchDto>("_id")
//                val data2 = result to MatchDto
//                val data = result to ArrayList<MatchDto>()
//                logger.info { data.first }
//                logger.info { data2.first }
//
//            }catch(e: Exception){
//                logger.error { "Error occurred gather matchIdList" }
////                return@runBlocking null
//            }
//        }
//    }
}