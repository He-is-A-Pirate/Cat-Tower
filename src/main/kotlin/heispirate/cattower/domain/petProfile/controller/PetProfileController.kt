package heispirate.cattower.domain.petProfile.controller

import heispirate.cattower.domain.petProfile.dto.PetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO
import heispirate.cattower.domain.petProfile.service.PetProfileService
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
    @PostMapping()
    fun createPetProfile(
        @PathVariable userId: Long,
        @RequestBody createPetProfileRequestDTO: PetProfileRequestDTO
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(petProfileService.createPetProfile(userId, createPetProfileRequestDTO))
    }
    @GetMapping("/{petId}")
    fun getPetProfile(
        @PathVariable userId: Long,
        @PathVariable petId: Long
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.getPetProfile(userId, petId))
    }
    @GetMapping()
    fun getUsersPetProfileList(
        @PathVariable userId: Long
    ): ResponseEntity<List<PetProfileResponseDTO>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.getUsersPetProfileList(userId))
    }

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

    @DeleteMapping("/{petId}")
    fun deletePetProfile(
        @PathVariable userId: Long,
        @PathVariable petId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(petProfileService.deletePetProfile(userId, petId))
    }

}