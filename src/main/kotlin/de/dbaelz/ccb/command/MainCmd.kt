package de.dbaelz.ccb.command

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.findOrSetObject
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option

class MainCmd : CliktCommand() {
    val verbose by option().flag("--no-verbose")
    val model by option("-m", "--model", help = "Model to use").default(DEFAULT_MODEL)
    val config by findOrSetObject { Config() }

    override fun run() {
        config.verbose = verbose
        config.model = model
    }
}

private const val DEFAULT_MODEL = "qwen3:8b"