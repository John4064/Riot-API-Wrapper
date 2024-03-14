import models.AccountDto
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.MatchDto

//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid


fun testApiCall(){
    val client = OkHttpClient();
    val userName: String = "tidal"
    val tagLine: String = "rcs"
    val request = Request.Builder()
        .url("https://americas.api.riotgames.com/riot/account/v1/accounts/by-riot-id/$userName/$tagLine")
        .get()
        .addHeader("X-Riot-Token", apiKey)
        .build();
    val response = client.newCall(request).execute();
    val jsonData: String = response.body.string()
    val accountData : AccountDto = Json.decodeFromString<AccountDto>(jsonData)
    val puuid: String = accountData.puuid
    val request2 = Request.Builder()
        .url("https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=0&count=5")
        .get()
        .addHeader("X-Riot-Token", apiKey)
        .build();
    val matchIdResponse = client.newCall(request2).execute();
    val matchList: String = matchIdResponse.body.string().replace("\"","")
    val elements = matchList.substring(1, matchList.length - 1).split(",")

    // Create an ArrayList from the elements
    val arrayList = ArrayList<String>(elements)

    println(arrayList.get(0))
    //https://americas.api.riotgames.com/lol/match/v5/matches/NA1_4942516854
    //Singular match lookup
    for (matchID in arrayList){
        val request3 = Request.Builder()
            .url("https://americas.api.riotgames.com/lol/match/v5/matches/%s".format(matchID))
            .get()
            .addHeader("X-Riot-Token", apiKey)
            .build();
        val tempResponse = client.newCall(request3).execute();//The Match Data Itself
        val tempJsonData: String = tempResponse.body.string().replace("12AssistStreakCount","assistStreakCount")
        val tempData : MatchDto = Json.decodeFromString<MatchDto>(tempJsonData)
        print(tempData.info.participants[0].championName +" ")
        print(tempData.info.participants[1].championName +" ")
        print(tempData.info.participants[2].championName +" ")
        print(tempData.info.participants[3].championName +" ")
        print(tempData.info.participants[4].championName +" ")
        print(": ")
        print(tempData.info.participants[5].championName +" ")
        print(tempData.info.participants[6].championName +" ")
        print(tempData.info.participants[7].championName +" ")
        print(tempData.info.participants[8].championName +" ")
        print(tempData.info.participants[9].championName +" ")
        print("- ")
    }

}

fun main(args: Array<String>) {
    testApiCall()

    //add header X-Riot-Token = to RGAPI

}