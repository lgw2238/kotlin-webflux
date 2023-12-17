package org.lgw.webfluxtoy

import mu.KotlinLogging
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.lgw.webfluxtoy.model.Article
import org.lgw.webfluxtoy.repository.WebFluxRepository
import org.lgw.webfluxtoy.service.ReqCreate
import org.lgw.webfluxtoy.service.WebFluxService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


private val logger = KotlinLogging.logger {}

@SpringBootTest
class WebFluxToyApplicationTests(
    @Autowired private val repository: WebFluxRepository,
    @Autowired private val service: WebFluxService,
    ) {

    @Test
    fun contextLoads() {
        val beforeCount = repository.count().block() ?: 0

        repository.save(Article(title = "title1")).block()
        val articles = repository.findAll().collectList().block()
        articles?.forEach { logger.debug { it } }


        val afterCount = repository.count().block() ?: 0
        Assertions.assertEquals(beforeCount + 1 , afterCount )

    }


    @Test
    fun createAndGet(){
        val beforeCnt = repository.count().block() ?:0
        val article = service.create(ReqCreate("title1", body = "title1" )).block()!!
        val afterCnt = repository.count().block() ?: 0
        Assertions.assertEquals(beforeCnt +1 , afterCnt)
        val readArticle = service.get(article.id).block()!!

        val a = assertEquals(article.id , readArticle.id)
        logger.info{ " a : " + a.toString() }

        assertEquals(article.title , readArticle.title)
        assertEquals(article.authorId, readArticle.authorId)
        assertEquals(article.body , readArticle.body)


    }

}
