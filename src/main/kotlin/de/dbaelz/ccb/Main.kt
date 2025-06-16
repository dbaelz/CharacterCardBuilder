package de.dbaelz.ccb

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.features.eventHandler.feature.handleEvents
import ai.koog.prompt.executor.llms.all.simpleOllamaAIExecutor
import ai.koog.prompt.llm.LLMCapability
import ai.koog.prompt.llm.LLMProvider
import ai.koog.prompt.llm.LLModel
import kotlinx.coroutines.runBlocking
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: java -jar CharacterCardBuilder.jar <characterDescription>")
        return
    }

    // Join all args in case user entered them with spaces instead of using quotes
    val characterDescription = args.joinToString(" ")
    
    val agent = AIAgent(
        executor = simpleOllamaAIExecutor(),
        systemPrompt = "You are an ai assistant to create character cards for role-play chat systems. " +
                "Use the following template: $CHARACTER_CARD_TEMPLATE",
        llmModel = LLModel(
            provider = LLMProvider.Ollama,
            id = MODEL,
            capabilities = listOf(
                LLMCapability.Temperature,
                LLMCapability.Schema.JSON.Simple,
                LLMCapability.Tools
            )
        )
    ) {
        handleEvents {
            onAgentRunError { strategyName: String, sessionUuid: Uuid?, throwable: Throwable ->
                println("An error occurred: ${throwable.message}\n${throwable.stackTraceToString()}")
            }

            onAgentFinished { strategyName: String, result: String? ->
                println(result)
            }
        }
    }

    runBlocking {
        agent.run("Create a character card for the character. Description of the character: '$characterDescription'")
    }
}

private const val MODEL = "qwen3:8b"

private const val CHARACTER_CARD_TEMPLATE = """
character_card = [
    "Name: ...",
    "Surname: ...",
    "Sex: ...",
    "Age: ...",
    "Nicknames: ...",
    "Personality: ..., ..., ...",
    "Body: ...",
    "Likes: ..., ..., ...",
    "Hates: ..., ..., ...",
    "Clothes: ...",
    "Species: ...",
    "Race: ...",
    "Occupation: ...",
    "Appearance: ...",
    "Description: ...",
    "Attributes: ..., ..., ..."
]
"""