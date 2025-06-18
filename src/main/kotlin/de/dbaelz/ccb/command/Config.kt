package de.dbaelz.ccb.command

data class Config(
    var verbose: Boolean = false,
    var model: String = "qwen3:8b"
)