package heispirate.cattower.infra.sms

import net.nurigo.sdk.NurigoApp.initialize
import net.nurigo.sdk.message.service.DefaultMessageService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
//class SmsConfig {
//
//    @Value("\${coolsms.api.key}")
//    private lateinit var apiKey: String
//
//    @Value("\${coolsms.api.secret}")
//    private lateinit var apiSecret: String
//
//    @Value("\${coolsms.api.url}")
//    private lateinit var apiUrl: String
//
//    @Bean
//    fun messageService(): DefaultMessageService {
//        return initialize(apiKey, apiSecret, apiUrl)
//    }
//}