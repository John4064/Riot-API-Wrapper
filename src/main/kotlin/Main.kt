//qIjNttqlsU_i_1B22gH9e3Bw0ugbFdGCIIxrGv0N-Te0d1OElK_dMCpvLjI-K6q4ECBpdWW62RcgVg for my acc uuid


fun main(args: Array<String>) {
    //Todo: After storing the data in the DB have checks made for existing data to reduce # of api calls for puuid and summonerID
//    val aggLogic: AggregationLogic = AggregationLogicImpl()
//    //Gather Ranked Data:
//    //val accountData: AccountDto? = aggLogic.getAccountData("Tidal","RCS")
//    //println(accountData.toString())
//    val userList: ArrayList<String> =
//        ArrayList<String>(Arrays.asList("Bombamamba", "Carnage", "Worthier", "T1 OK GOOD YES","papa bard"))
//    val tagList: ArrayList<String> = ArrayList<String>(Arrays.asList("na1", "RCS", "na1", "na1","meep"))
//    val uri = "mongodb://john4064:Panda1234@code-catalyst.com:42198/"
//    val mongoClient = MongoClient.create(uri)
//    val database = mongoClient.getDatabase("league-stats")
//    val collection = database.getCollection<SummonerDto>("Summoners")
//    runBlocking {
//        for (i in 0..<1) {
//            val summonerData: SummonerDto? = aggLogic.getSummonerDataByUsername(userList.get(i), tagList.get(i))
//            if(summonerData!= null){
//                //Swap this to insert many
//                //as well as a check for existing copies of the document
//                try{
//                    val result = collection.insertOne(summonerData)
//                    println("Successfully inserted summoner: ${summonerData.name}")
//                }catch(e: MongoWriteException) {
//                    println("User already exists:  ${summonerData.name}" )
//                }catch(e: Exception){
//                    println("Error occured inserting summoner: ${summonerData.name}")
//                }
//            }
//        }
//    }
//    mongoClient.close()

}