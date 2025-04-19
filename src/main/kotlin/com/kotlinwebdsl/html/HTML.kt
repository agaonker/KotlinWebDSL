package com.kotlinwebdsl.html

sealed class HTMLNode {
    abstract fun render(): String
}

class HTMLDocument(private val content: HTMLNode) : HTMLNode() {
    override fun render(): String {
        return "<!DOCTYPE html>\n${content.render()}"
    }
}

class HTMLTag(
    private val name: String,
    private val attributes: Map<String, String> = emptyMap(),
    private val selfClosing: Boolean = false
) : HTMLNode() {
    private val children = mutableListOf<HTMLNode>()

    fun addChild(child: HTMLNode) {
        children.add(child)
    }

    override fun render(): String {
        val attrs = if (attributes.isEmpty()) "" else
            " " + attributes.entries.joinToString(" ") { "${it.key}=\"${it.value}\"" }
        
        if (selfClosing) {
            return "<$name$attrs />"
        }
        
        val childrenContent = children.joinToString("\n") { it.render() }
        return "<$name$attrs>$childrenContent</$name>"
    }
}

class TextNode(private val text: String) : HTMLNode() {
    override fun render(): String = text
}

// DSL Builder Functions
fun html(init: HTMLTag.() -> Unit): HTMLDocument {
    val html = HTMLTag("html")
    html.init()
    return HTMLDocument(html)
}

fun HTMLTag.head(init: HTMLTag.() -> Unit) {
    val head = HTMLTag("head")
    head.init()
    addChild(head)
}

fun HTMLTag.body(init: HTMLTag.() -> Unit) {
    val body = HTMLTag("body")
    body.init()
    addChild(body)
}

fun HTMLTag.title(text: String) {
    val title = HTMLTag("title")
    title.addChild(TextNode(text))
    addChild(title)
}

fun HTMLTag.meta(charset: String) {
    addChild(HTMLTag("meta", mapOf("charset" to charset), selfClosing = true))
}

fun HTMLTag.h1(text: String) {
    val h1 = HTMLTag("h1")
    h1.addChild(TextNode(text))
    addChild(h1)
}

fun HTMLTag.p(text: String) {
    val p = HTMLTag("p")
    p.addChild(TextNode(text))
    addChild(p)
}

fun HTMLTag.div(init: HTMLTag.() -> Unit) {
    val div = HTMLTag("div")
    div.init()
    addChild(div)
}

fun HTMLTag.span(init: HTMLTag.() -> Unit) {
    val span = HTMLTag("span")
    span.init()
    addChild(span)
}

fun HTMLTag.a(href: String, text: String) {
    val a = HTMLTag("a", mapOf("href" to href))
    a.addChild(TextNode(text))
    addChild(a)
}

fun HTMLTag.img(src: String, alt: String) {
    addChild(HTMLTag("img", mapOf("src" to src, "alt" to alt), selfClosing = true))
}

fun HTMLTag.ul(init: HTMLTag.() -> Unit) {
    val ul = HTMLTag("ul")
    ul.init()
    addChild(ul)
}

fun HTMLTag.li(text: String) {
    val li = HTMLTag("li")
    li.addChild(TextNode(text))
    addChild(li)
} 