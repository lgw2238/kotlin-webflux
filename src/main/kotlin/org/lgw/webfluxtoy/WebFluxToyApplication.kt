package org.lgw.webfluxtoy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebFluxToyApplication

fun main(args: Array<String>) {
    runApplication<WebFluxToyApplication>(*args)
}
