package de.dbaelz.ccb

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.arguments.argument
import de.dbaelz.ccb.builder.DEFAULT_CHARACTER_CARD_TEMPLATE
import de.dbaelz.ccb.builder.SimpleCharacterCardBuilder
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = SimpleDescriptionCommand().main(args)

class SimpleDescriptionCommand : CliktCommand(name = "characterCardBuilder") {
    private val characterDescription by argument(
        name = "character description",
        help = "The description of the character"
    )

    override fun run() {
        runBlocking {
            val result = SimpleCharacterCardBuilder(
                model = MODEL,
                template = DEFAULT_CHARACTER_CARD_TEMPLATE,
                characterDescription = characterDescription
            ).run()

            echo(result)
        }
    }
}

private const val MODEL = "qwen3:8b"