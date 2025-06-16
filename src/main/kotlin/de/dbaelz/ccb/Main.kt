package de.dbaelz.ccb

import de.dbaelz.ccb.builder.DEFAULT_CHARACTER_CARD_TEMPLATE
import de.dbaelz.ccb.builder.SimpleCharacterCardBuilder
import kotlinx.coroutines.runBlocking
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class)
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar CharacterCardBuilder.jar <characterDescription>")
        return
    }

    // Join all args in case user entered them with spaces instead of using quotes
    val characterDescription = args.joinToString(" ")

    runBlocking {
        println(
            SimpleCharacterCardBuilder(
                model = MODEL,
                template = DEFAULT_CHARACTER_CARD_TEMPLATE,
                characterDescription = characterDescription
            ).run()
        )
    }
}

private const val MODEL = "qwen3:8b"