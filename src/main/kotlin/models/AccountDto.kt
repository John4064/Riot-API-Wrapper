package models

import kotlinx.serialization.Serializable

//puuid 	string
//gameName 	string 	This field may be excluded from the response if the account doesn't have a gameName.
//tagLine 	string
@Serializable
data class AccountDto(
    val puuid: String,
    val gameName: String,
    val tagLine: String
)
