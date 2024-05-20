package heispirate.cattower

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class CatTowerApplication

fun main(args: Array<String>) {
    runApplication<CatTowerApplication>(*args)
}
