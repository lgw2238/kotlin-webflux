package org.lgw.webfluxtoy.repository

import org.lgw.webfluxtoy.model.Article
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface WebFluxRepository : R2dbcRepository<Article, Long> {

    fun findAllByTitleContains(title: String): Flux<Article>
}