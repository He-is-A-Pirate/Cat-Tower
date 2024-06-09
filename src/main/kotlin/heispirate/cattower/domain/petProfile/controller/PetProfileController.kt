package heispirate.cattower.domain.petProfile.controller

import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO
import heispirate.cattower.domain.petProfile.service.PetProfileService
import io.swagger.v3.oas.annotations.Operation
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

@RestController
@RequestMapping("/users/{userId}/pets")
class PetProfileController(
    private val petProfileService: PetProfileService
) {

    @Operation(summary = "펫 프로필 생성", description = "사용자가 새로운 펫 프로필을 생성합니다.")
    @PostMapping()
    fun createPetProfile(
        @PathVariable userId: Long,
        @RequestBody createPetProfileRequestDTO: PetProfileRequestDTO
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(petProfileService.createPetProfile(userId, createPetProfileRequestDTO))
    }
    @Operation(summary = "펫 프로필 조회", description = "특정 펫 프로필을 조회합니다.")
    @GetMapping("/{petId}")
    fun getPetProfile(
        @PathVariable userId: Long,
        @PathVariable petId: Long
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.getPetProfile(userId, petId))
    }

    @Operation(summary = "펫 프로필 목록 조회", description = "특정 사용자의 펫 프로필 목록을 조회합니다.")
    @GetMapping()
    fun getUsersPetProfileList(
        @PathVariable userId: Long
    ): ResponseEntity<List<PetProfileResponseDTO>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.getUsersPetProfileList(userId))
    }

    @Operation(summary = "펫 프로필 수정", description = "특정 펫 프로필을 수정합니다.")
    @PutMapping("/{petId}")
    fun updatePetProfile(
        @PathVariable userId: Long,
        @PathVariable petId: Long,
        @RequestBody petProfileRequestDTO: PetProfileRequestDTO
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.updatePetProfile(userId, petId, petProfileRequestDTO))
    }

    @Operation(summary = "펫 프로필 삭제", description = "특정 펫 프로필을 삭제합니다.")
    @DeleteMapping("/{petId}")
    fun deletePetProfile(
        @PathVariable userId: Long,
        @PathVariable petId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(petProfileService.deletePetProfile(userId, petId))
    }

}