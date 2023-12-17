package org.lgw.webfluxtoy.controller

import mu.KotlinLogging
import org.lgw.webfluxtoy.model.Article
import org.lgw.webfluxtoy.service.ReqCreate
import org.lgw.webfluxtoy.service.ReqUpdate
import org.lgw.webfluxtoy.service.WebFluxService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/webflux")
class WebFluxController(
    private val service: WebFluxService,
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: ReqCreate): Mono<Article> {
        return service.create(request)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Mono<Article> {
        return service.get(id)
    }

    @GetMapping("/all")
    fun getAll(@RequestParam title: String?): Flux<Article> {
        return service.getAll(title)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ReqUpdate): Mono<Article> {
        logger.debug { ">> request: $request" }
        return service.update(id, request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Mono<Void> {
        return service.delete(id)
    }

}