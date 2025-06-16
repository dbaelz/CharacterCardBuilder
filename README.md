# Character Card Builder
Character Card Builder is a demo for the [koog](https://github.com/JetBrains/koog) framework to build AI agents with Kotlin.
The tool uses a local Ollama instance to build character cards that can be used in role-play chat systems or for Pen & Paper sessions.

## Requirements
The project is a demo and work in progress. It requires the following environment:
- A running local Ollama instance to connect to
- Qwen3 8b model available with Ollama (or change [MODEL constant in Main.kt](src/main/kotlin/de/dbaelz/ccb/Main.kt))

## How to use
1. Run the application and provide the character description as an argument. For example: `./gradlew run --args="'Ada Lovelace'"`
2. Add the name or a description of the character as one argument in quotes or as multiple arguments

### Example Arguments
- Name of the character. Works best with historical characters: `"Ada Lovelace"` or `"Historical character Ada Lovelace"`
- You can also describe the character: `"A fictional character named John Doe. He is a 42 years old software developer who is passionate about AI. 170 cm height, brown hair and blue eyes."`

## Contribution
Feel free to contribute via pull requests.

## License
The project is licensed by the [Apache 2 license](LICENSE).