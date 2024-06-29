package logic

import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.IngestionLogic
import logic.interfaces.RiotLogic
import models.SummonerDto
import java.util.*
import kotlin.collections.ArrayList

//JUST SERVICE CLASS JUST TO SAVE TEST DATA
class LocalLogicImpl {

    private val logger = KotlinLogging.logger {}

    fun gatherUserDataExample(){
        val aggLogic: RiotLogic = RiotLogicImpl()
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

}