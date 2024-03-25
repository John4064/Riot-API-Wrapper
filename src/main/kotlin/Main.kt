import config.AppConfig
import models.AccountDto
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import logic.AggregationLogicImpl
import logic.interfaces.AggregationLogic
import models.MatchDto
import models.SummonerDto

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid

fun main(args: Array<String>) {
    //Todo: After storing the data in the DB have checks made for existing data to reduce # of api calls for puuid and summonerID
    val aggLogic : AggregationLogic = AggregationLogicImpl()
    if(false){
        val matchIDList: ArrayList<String> = aggLogic.getMatchIDs("Santa","Daddy",3)
        if(matchIDList.size>0){
            for (matchID in matchIDList){
                val matchData: MatchDto? = aggLogic.getMatchData(matchID)
                if (matchData != null) {
                    println(matchData.info.gameMode)
                    println(matchData.info.gameType)
                    println(matchData.info.gameName)
2                }
            }
        }else{
            println("Matches not found")
        }
    }else{
        //Gather Ranked Data:
//        val accountData: AccountDto? = aggLogic.getAccountData("Tidal","RCS")
//        println(accountData.toString())
        val summonerData: SummonerDto? = aggLogic.getSummonerDataByPuuid("qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg")
        val sumData: SummonerDto? = aggLogic.getSummonerDataByUsername("Tidal","RCS")
        println(summonerData.toString())
        println(sumData.toString())
    }
}