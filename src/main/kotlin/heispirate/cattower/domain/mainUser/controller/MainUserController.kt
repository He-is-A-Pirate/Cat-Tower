package heispirate.cattower.domain.mainUser.controller

import heispirate.cattower.domain.mainUser.dto.MainUserRequestDTO
import heispirate.cattower.domain.mainUser.dto.MainUserResponseDTO
import heispirate.cattower.domain.mainUser.dto.SingUpRequestDTO
import heispirate.cattower.domain.mainUser.service.MainUserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.constraints.Null
import org.springframework.context.annotation.Description
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
    }//회원가입
    @GetMapping("/users/{userId}")
    @Operation(summary = "회원정보조회", description = "회원정보 조회 기능")
    fun getUser(
        @PathVariable userId: Long
    ): ResponseEntity<MainUserResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mainUserService.getUser(userId))
    }//회원정보조회
    @PutMapping("/users/{userId}")
    fun updateUser(@PathVariable userId: Long,mainUserRequestDTO: MainUserRequestDTO):ResponseEntity<MainUserResponseDTO>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(mainUserService.updateUser(userId, mainUserRequestDTO))
    }//회원정보수정
    

}//회원정보 생성(가입) /조회 / 수정 / 삭제 / login