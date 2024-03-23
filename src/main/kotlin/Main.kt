import config.AppConfig
import models.AccountDto
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import logic.AggregationLogicImpl
import logic.interfaces.AggregationLogic
import models.MatchDto

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid

fun main(args: Array<String>) {
//    testApiCall("")
    val aggLogic : AggregationLogic = AggregationLogicImpl()
    //val accountData : AccountDto?=aggLogic.getAccountData("Tidal","RCS")
    if(true){
        val matchIDList: ArrayList<String> = aggLogic.getMatchIDs("Tidal","RCS",10)
        if(matchIDList.size>0){
            for (matchID in matchIDList){
                val matchData: MatchDto? = aggLogic.getMatchData(matchID)
                println(matchData.toString())
            }
        }else{
            println("Matches not found")
        }
    }
    //add header X-Riot-Token = to RGAPI

}