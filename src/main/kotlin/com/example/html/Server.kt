package com.example.html

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                val htmlDocument = html {
                    head {
                        title("HTML DSL Example")
                        meta("UTF-8")
                    }
                    body {
                        h1("Welcome to HTML DSL")
                        p("This is a paragraph created using our HTML DSL")
                        div {
                            p("This is a nested paragraph inside a div")
                            span {
                                p("This is a deeply nested paragraph")
                            }
                        }
                        ul {
                            li("First item")
                            li("Second item")
                            li("Third item")
                        }
                        a("https://example.com", "Visit Example.com")
                        img("https://picsum.photos/200/300", "Random Image")
                    }
                }
                
                call.respondText(htmlDocument.render(), ContentType.Text.Html)
            }
        }
    }.start(wait = true)
} 