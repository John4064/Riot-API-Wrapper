import io.github.oshai.kotlinlogging.KotlinLogging
import logic.AggregationLogicImpl
import logic.IngestionLogicImpl
import logic.interfaces.AggregationLogic
import logic.interfaces.IngestionLogic
import models.SummonerDto
import java.util.*
import kotlin.collections.ArrayList

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid
private val logger = KotlinLogging.logger {}


fun main(args: Array<String>) {
    //Todo: After storing the data in the DB have checks made for existing data to reduce # of api calls for puuid and summonerID
    val aggLogic: AggregationLogic = AggregationLogicImpl()
    val userList: ArrayList<String> =
        ArrayList<String>(Arrays.asList("Doublelift", "Carnage", "Worthier", "T1 OK GOOD YES","papa bard"))
    val tagList: ArrayList<String> = ArrayList<String>(Arrays.asList("na1", "RCS", "na1", "na1","meep"))
    val ingestionLogic: IngestionLogic = IngestionLogicImpl()
    val summonerData: SummonerDto? = aggLogic.getSummonerDataByUsername(userList.get(0), tagList.get(0))
    if(summonerData!= null){
        logger.info { "INSERTING: ${summonerData.name}" }
        ingestionLogic.insertSummonerData(summonerData)
        logger.info { "Successful" }
    }else{
        logger.error { "Summoner Data was not found" }
    }
}