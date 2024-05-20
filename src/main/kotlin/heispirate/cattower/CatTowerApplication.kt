package heispirate.cattower

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class CatTowerApplication

fun main(args: Array<String>) {
    runApplication<CatTowerApplication>(*args)
}
