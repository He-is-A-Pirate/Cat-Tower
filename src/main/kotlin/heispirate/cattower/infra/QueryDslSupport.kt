package heispirate.cattower.infra

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component


abstract class QueryDslSupport {
    @PersistenceContext
    protected lateinit var entityManager: EntityManager

    protected val queryFactory: JPAQueryFactory
        get() {
            return JPAQueryFactory(entityManager)
        }
}
