package de.dbaelz.ccb.builder

import ai.koog.agents.core.agent.AIAgent
import ai.koog.agents.features.eventHandler.feature.handleEvents
import ai.koog.prompt.executor.llms.all.simpleOllamaAIExecutor
import ai.koog.prompt.llm.LLMCapability
import ai.koog.prompt.llm.LLMProvider
import ai.koog.prompt.llm.LLModel
import kotlinx.coroutines.CompletableDeferred
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class SimpleCharacterCardBuilder(
    model: String,
    template: String,
    private val characterDescription: String
) : CharacterCardBuilder {
    private val resultDeferred = CompletableDeferred<String>()

    @OptIn(ExperimentalUuidApi::class)
    val agent = AIAgent(
        executor = simpleOllamaAIExecutor(),
        systemPrompt = "You are an ai assistant to create character cards for role-play chat systems. " +
                "Use the following template: $template",
        llmModel = LLModel(
            provider = LLMProvider.Ollama,
            id = model,
            capabilities = listOf(
                LLMCapability.Temperature,
                LLMCapability.Schema.JSON.Simple,
                LLMCapability.Tools
            )
        )
    ) {
        handleEvents {
            onAgentRunError { strategyName: String, sessionUuid: Uuid?, throwable: Throwable ->
                resultDeferred.completeExceptionally(exception = throwable)
            }

            onAgentFinished { strategyName: String, result: String? ->
                resultDeferred.complete(result ?: "An error occurred: Null result returned.")
            }
        }
    }

    override suspend fun run(): String {
        agent.run("Create a character card for the character. Description of the character: '$characterDescription'")
        return resultDeferred.await()
    }
}