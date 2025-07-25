/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    application
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo1.maven.org/maven2/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation(libs.org.jetbrains.kotlin.kotlin.serialization)
    implementation(libs.org.jetbrains.kotlin.kotlin.stdlib)
    implementation(libs.com.squareup.okhttp3.okhttp)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.core.jvm)
    implementation(libs.org.jetbrains.kotlinx.kotlinx.serialization.json)
    implementation(libs.org.mongodb.mongodb.driver.kotlin.coroutine)
    implementation(libs.org.mongodb.bson.kotlinx)
    implementation(libs.io.github.oshai.kotlin.logging.jvm)
//    implementation("org.slf4j:slf4j-implementation:1.7.25")
    implementation("org.slf4j:slf4j-simple:1.7.25")
    implementation(libs.org.jetbrains.exposed.exposed.core)
    implementation(libs.org.jetbrains.exposed.exposed.jdbc)
    implementation(libs.org.jetbrains.exposed.exposed.dao)
    implementation(libs.org.jetbrains.exposed.exposed.json)
    implementation(libs.org.postgresql.postgresql)
    testImplementation(libs.org.jetbrains.kotlin.kotlin.test.junit5)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.engine)
}

group = "com.code.catalyst"
version = "1.0"
description = "Backend Data Aggregator"
java.sourceCompatibility = JavaVersion.VERSION_21


tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}

