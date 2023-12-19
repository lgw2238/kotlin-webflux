package org.lgw.webfluxtoy.controller

import io.swagger.v3.oas.annotations.Operation
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

    @GetMapping("/{id}")
    @Operation(summary = "게시물 단건 개별조회")
    fun get(@PathVariable id: Long): Mono<Article> {
        return service.get(id)
    }

    @GetMapping("/all")
    @Operation(summary = "게시물 전체 조회")
    fun getAll(@RequestParam title: String?): Flux<Article> {
        return service.getAll(title)
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "게시물 생성")
    fun create(@RequestBody request: ReqCreate): Mono<Article> {
        logger.info(request.toString());
        return service.create(request)
    }

    @PutMapping("/{id}")
    @Operation(summary = "게시물 수정")
    fun update(@PathVariable id: Long, @RequestBody request: ReqUpdate): Mono<Article> {
        logger.debug { ">> request: $request" }
        return service.update(id, request)
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "게시물 삭제")
    fun delete(@PathVariable id: Long): Mono<Void> {
        return service.delete(id)
    }

}


