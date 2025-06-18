package de.dbaelz.ccb.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.arguments.argument
import de.dbaelz.ccb.builder.DEFAULT_CHARACTER_CARD_TEMPLATE
import de.dbaelz.ccb.builder.SimpleCharacterCardBuilder
import kotlinx.coroutines.runBlocking

class PromptDescriptionCmd() : CliktCommand(name = "prompt") {
    private val description by argument(
        name = "description",
        help = "The description of the character"
    )

    val config by requireObject<Config>()

    override fun run() {
        if (config.verbose) {
            echo("Executing prompt '${description}' with model ${config.model}")
        }

        runBlocking {
            val result = SimpleCharacterCardBuilder(
                model = config.model,
                template = DEFAULT_CHARACTER_CARD_TEMPLATE,
                characterDescription = description
            ).run()

            echo(result)
        }
    }
}