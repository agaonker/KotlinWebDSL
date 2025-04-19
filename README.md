# KotlinWebDSL

A Kotlin-based HTML DSL (Domain-Specific Language) for server-side rendering. This library allows you to create HTML documents using a type-safe, Kotlin-friendly DSL.

## Features

- Type-safe HTML document creation
- Server-side rendering support
- Support for all basic HTML elements
- Clean and intuitive DSL syntax
- Built with Ktor for web server integration

## Getting Started

### Prerequisites

- Kotlin 1.9.0 or higher
- Java 17 or higher
- Gradle 7.5 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/agaonker/KotlinWebDSL.git
```

2. Build the project:
```bash
gradle build
```

### Usage

```kotlin
val htmlDocument = html {
    head {
        title("My Page")
        meta("UTF-8")
    }
    body {
        h1("Welcome")
        p("This is a paragraph")
        div {
            p("Nested content")
        }
    }
}

println(htmlDocument.render())
```

## Example Server

The project includes a simple Ktor server that demonstrates how to use the DSL:

```kotlin
fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                val htmlDocument = html {
                    // Your HTML content here
                }
                call.respondText(htmlDocument.render(), ContentType.Text.Html)
            }
        }
    }.start(wait = true)
}
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the LICENSE file for details. 