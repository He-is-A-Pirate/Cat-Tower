package heispirate.cattower.domain.petProfile.controller

import heispirate.cattower.domain.petProfile.dto.CreatePetProfileRequestDTO
import heispirate.cattower.domain.petProfile.dto.PetProfileResponseDTO
import heispirate.cattower.domain.petProfile.service.PetProfileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
        @RequestBody createPetProfileRequestDTO: CreatePetProfileRequestDTO
    ): ResponseEntity<PetProfileResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(petProfileService.createPetProfile(userId, createPetProfileRequestDTO))
    }


}