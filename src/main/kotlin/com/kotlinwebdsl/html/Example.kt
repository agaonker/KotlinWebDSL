package com.kotlinwebdsl.html

fun main() {
    val htmlDocument = html {
        head {
            title("My First HTML Page")
            meta("UTF-8")
        }
        body {
            h1("Welcome to My Website")
            p("This is a paragraph of text.")
            div {
                p("This is a paragraph inside a div.")
                span {
                    p("This is a paragraph inside a span.")
                }
            }
            ul {
                li("First item")
                li("Second item")
                li("Third item")
            }
            a("https://example.com", "Visit Example.com")
            img("image.jpg", "An example image")
        }
    }

    println(htmlDocument.render())
} 