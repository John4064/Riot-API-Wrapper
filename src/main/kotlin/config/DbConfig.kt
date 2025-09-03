package config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.v1.jdbc.Database
import kotlin.system.exitProcess


fun connectToDatabase() {
    val logger = KotlinLogging.logger {}
    val dbUser: String = System.getenv("dbUser") ?: ""
    val dbPass: String = System.getenv("dbPass") ?: ""
    val dbUrl: String = System.getenv("dbUrl")
    try {
        Database.connect(
            url = "jdbc:postgresql://$dbUrl",
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPass
        )
        logger.info { "Successfully Connected: $dbUser" }
    } catch(e: Exception) {
        logger.error { "Error Connecting to DB: ${e.toString()}" }
        exitProcess(-1)
    }
}