package de.dbaelz.ccb.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject

class WorkflowCmd : CliktCommand(name = "workflow") {

    private val config by requireObject<Config>()

    override fun run() {
        if (config.verbose) {
            echo("Executing workflow with model ${config.model}")
        }

        // TODO: Add workflow to ask for information about the character

    }

}