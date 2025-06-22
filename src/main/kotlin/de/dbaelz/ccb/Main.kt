package de.dbaelz.ccb

import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import de.dbaelz.ccb.command.MainCmd
import de.dbaelz.ccb.command.PromptDescriptionCmd
import de.dbaelz.ccb.command.WorkflowCmd

fun main(args: Array<String>) = MainCmd().subcommands(
    PromptDescriptionCmd(),
    WorkflowCmd()
).main(args)