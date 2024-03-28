package config

import java.io.FileReader
import java.util.*


class AppConfig {
    var apiKey: String=""
    var mongodbUri: String=""
    fun loadFromFile(fileName: String) {
        val properties = Properties()
        properties.load(FileReader("src/main/resources/$fileName"))
        apiKey = properties.getProperty("API_KEY")
        mongodbUri = properties.getProperty("MONGODB_URI")
    }
}