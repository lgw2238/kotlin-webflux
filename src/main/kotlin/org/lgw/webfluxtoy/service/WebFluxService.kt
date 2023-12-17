package org.lgw.webfluxtoy.service

import org.lgw.webfluxtoy.exception.NoArticleException
import org.lgw.webfluxtoy.model.Article
import org.lgw.webfluxtoy.repository.WebFluxRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class WebFluxService (
    private val repository: WebFluxRepository, )
{
    @Transactional
    fun create(request: ReqCreate) : Mono<Article> {
        return repository.save(request.toArticle())
    }

    fun get(id: Long):Mono<Article> {
        return repository.findById(id)
            .switchIfEmpty { throw NoArticleException("id: $id") }
    }

}

data class ReqCreate(
    val title : String,
    val body : String? = null,
    val authorId : Long? = null
){
    fun toArticle() : Article {
        return Article (
            title = this.title,
            body = this.body,
            authorId = this.authorId,
        )
    }
}