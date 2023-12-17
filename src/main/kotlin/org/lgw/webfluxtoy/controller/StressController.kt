package org.lgw.webfluxtoy.controller

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
private val logger = KotlinLogging.logger {}

@RestController
class StressController(
    @Value("\${api.externalUrl}")
    private val externalUrl: String
) {

    private val client = WebClient.builder().baseUrl(externalUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    @GetMapping("/stress/delay")
    fun delay(): Mono<String> {
        logger.debug { "requested" }
        return client.get().uri("/delay").retrieve().bodyToMono<String>()
    }
}