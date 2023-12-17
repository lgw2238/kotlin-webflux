package org.lgw.webfluxtoy.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("TB_ARTICLE")
class Article (
    @Id
    var id: Long = 0,
    var title: String,
    var body: String? = null,
    var authorId: Long? = null,
): BaseEntity() {

    override fun toString(): String {
        return "Article(id=$id, title='$title', body='$body', authorId=$authorId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

}


open class BaseEntity(
    var createAt: LocalDateTime? = null,
    var updateAt: LocalDateTime? = null,
) {
    override fun toString(): String {
        return "createAt:=$createAt" +","+ "updateAt:$updateAt";
    }
}