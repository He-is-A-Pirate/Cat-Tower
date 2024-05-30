package heispirate.cattower.domain.mainUser.controller

import heispirate.cattower.domain.mainUser.dto.MainUserResponseDTO
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO
import heispirate.cattower.domain.mainUser.service.MainUserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.context.annotation.Description
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RestController
class MainUserController(
    private val mainUserService: MainUserService
) {

    @PostMapping("/users/signup")
    @Operation(summary = "회원가입" , description = "일반유저 회원가입 입니다")
    fun signUp(
        @RequestBody signUpRequestDTO: SingUpRequestDTO
    ):ResponseEntity<Unit>{
        mainUserService.signUp(signUpRequestDTO)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .build()
    }

}