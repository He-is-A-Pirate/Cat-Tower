package heispirate.cattower.domain.authCode.controller

import heispirate.cattower.domain.authCode.dto.AuthResponseDTO
import heispirate.cattower.domain.authCode.service.AuthCodeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auths")
class AuthCodeController(
    private val authCodeService: AuthCodeService
) {
    @PostMapping("/email")
    @Operation(summary = "인증메일 전송" , description = "인증메일 전송기능")
    fun sendEmail(
        @RequestParam email: String,
    ): ResponseEntity<AuthResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authCodeService.sendAuthEmail(email))
    }

    @PutMapping("/verify")
    @Operation(summary = "인증코드 확인", description = "인증코드 확인")
    fun verifyCode(
        @RequestParam code: String,
        @RequestParam email: String,
    ): ResponseEntity<AuthResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authCodeService.verifyCode(email,code))
    }

}