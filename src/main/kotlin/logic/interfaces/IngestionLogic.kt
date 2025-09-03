package logic.interfaces

import models.AccountDto
import models.InfoDto
import models.MatchDto
import models.MetadataDto
import models.SummonerDto

interface IngestionLogic {




    fun insertSummonerData(summonerData: SummonerDto)
    fun insertAccountData(accountData: AccountDto)
    fun insertMatchData(matchData: MatchDto,username: String,tagline: String)
    fun insertUserMatchData(matchId: String, username: String, tagline: String)

    fun insertInfoData(infoData: InfoDto): Long
    fun insertMetadataData(metaData: MetadataDto): Long
}
