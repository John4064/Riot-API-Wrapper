package logic

import config.AppConfig
import config.connectToDatabase
import entities.Match
import io.github.oshai.kotlinlogging.KotlinLogging
import logic.interfaces.RetrievalService
import org.jetbrains.exposed.v1.jdbc.select
import repo.MatchRepo


class RetrievalServiceImpl : RetrievalService {
    private lateinit var config: AppConfig

    private val logger = KotlinLogging.logger {}
    private lateinit var matchRepo: MatchRepo

    init {

        config = AppConfig()
        config.loadFromFile("application.properties")
        matchRepo = MatchRepo()

        logger.info { "Retrieval logic is initialized." }
    }
    override fun checkMatchExist(matchId: String): Boolean{
        return matchRepo.checkMatchExist(matchId)
    }
}