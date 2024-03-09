import models.AccountDto
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid

public val apiKey: String ="a"

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
        .url("https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=0&count=100")
        .get()
        .addHeader("X-Riot-Token", apiKey)
        .build();
    val matchIdResponse = client.newCall(request2).execute();
    val matchList: String = matchIdResponse.body.string().replace("\"","")
    val elements = matchList.substring(1, matchList.length - 1).split(",")

    // Create an ArrayList from the elements
    val arrayList = ArrayList<String>(elements)

    println(arrayList.get(0))
}

fun main(args: Array<String>) {
    testApiCall()


    //add header X-Riot-Token = to RGAPI

}