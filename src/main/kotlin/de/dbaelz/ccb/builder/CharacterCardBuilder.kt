package de.dbaelz.ccb.builder

interface CharacterCardBuilder {
    // TODO Kotlin 2.4: Change return value to a string or error (rich errors feature)
    suspend fun run(): String
}