package com.kotlinwebdsl.html

import kotlin.test.Test
import kotlin.test.assertEquals

class HTMLTest {
    @Test
    fun `test basic HTML structure`() {
        val htmlDocument = html {
            head {
                title("Test Page")
                meta("UTF-8")
            }
            body {
                h1("Hello World")
            }
        }

        val expected = """
            <!DOCTYPE html>
            <html>
            <head>
            <title>Test Page</title>
            <meta charset="UTF-8" />
            </head>
            <body>
            <h1>Hello World</h1>
            </body>
            </html>
        """.trimIndent()

        assertEquals(expected, htmlDocument.render())
    }

    @Test
    fun `test nested elements`() {
        val htmlDocument = html {
            body {
                div {
                    p("Nested paragraph")
                    span {
                        p("Deeply nested paragraph")
                    }
                }
            }
        }

        val expected = """
            <!DOCTYPE html>
            <html>
            <body>
            <div>
            <p>Nested paragraph</p>
            <span>
            <p>Deeply nested paragraph</p>
            </span>
            </div>
            </body>
            </html>
        """.trimIndent()

        assertEquals(expected, htmlDocument.render())
    }

    @Test
    fun `test list structure`() {
        val htmlDocument = html {
            body {
                ul {
                    li("First item")
                    li("Second item")
                }
            }
        }

        val expected = """
            <!DOCTYPE html>
            <html>
            <body>
            <ul>
            <li>First item</li>
            <li>Second item</li>
            </ul>
            </body>
            </html>
        """.trimIndent()

        assertEquals(expected, htmlDocument.render())
    }

    @Test
    fun `test attributes`() {
        val htmlDocument = html {
            body {
                a("https://example.com", "Example Link")
                img("image.jpg", "Test Image")
            }
        }

        val expected = """
            <!DOCTYPE html>
            <html>
            <body>
            <a href="https://example.com">Example Link</a>
            <img src="image.jpg" alt="Test Image" />
            </body>
            </html>
        """.trimIndent()

        assertEquals(expected, htmlDocument.render())
    }
} 