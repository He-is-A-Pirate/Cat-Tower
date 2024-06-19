package heispirate.cattower

import kotlinx.datetime.TimeZone
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaAuditing
class CatTowerApplication

fun main(args: Array<String>) {
    java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<CatTowerApplication>(*args)
}
