package de.dbaelz

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
fun main() {
    val agent = AIAgent(
        executor = simpleOllamaAIExecutor(),
        systemPrompt = "You are an ai assistant to create character cards for role-play chat systems. " +
                "Use the following template: $CHARACTER_CARD_TEMPLATE",
        llmModel = LLModel(
            provider = LLMProvider.Ollama,
            id = "qwen3:8b",
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
        agent.run("Create a character card for the historical character 'Ada Lovelace'")
    }
}

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