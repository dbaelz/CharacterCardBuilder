# Character Card Builder
Character Card Builder is a demo for the [koog](https://github.com/JetBrains/koog) framework to build AI agents with Kotlin.
The tool uses a local Ollama instance to build character cards that can be used in role-play chat systems or for Pen & Paper sessions.

## Requirements
The project is a demo and work in progress. It requires the following environment:
- A running local Ollama instance to connect to
- Model available with Ollama. The default is `qwen3:8b` (see [Config.kt](src/main/kotlin/de/dbaelz/ccb/command/Config.kt)), but can be changed with an arg

## How to use: prompt command
The prompt command uses a single prompt to generate the character card. Run the application and provide the description `./gradlew run --args="prompt 'Ada Lovelace'"`

Example Arguments for prompt command:
- Name of the character. Works best with historical characters: `"Ada Lovelace"` or `"Historical character Ada Lovelace"`
- You can also describe the character: `"A fictional character named John Doe. He is a 42 years old software developer who is passionate about AI. 170 cm height, brown hair and blue eyes."`

## Addition arguments and options
All additional arguments and options can be listed with the `--help` (run `./gradlew run --args="--help"`)
- `--verbose`: Verbose output
- `--model`/`-m`: Specify the model to use. E.g. `./gradlew run --args="--model 'mymodel:latest' prompt 'Ada Lovelace'"`


## Contribution
Feel free to contribute via pull requests.

## License
The project is licensed by the [Apache 2 license](LICENSE).