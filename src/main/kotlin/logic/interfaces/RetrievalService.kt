package logic.interfaces

interface RetrievalService {
    //Simple CRUD services
//    fun getMatchData(matchId: String)
//
//    fun gatherMatchIds()
    fun checkMatchExist(matchId: String): Boolean

}