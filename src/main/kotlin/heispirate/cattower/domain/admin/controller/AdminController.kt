package heispirate.cattower.domain.admin.controller

import heispirate.cattower.domain.admin.dto.AdminRequestDTOver1
import heispirate.cattower.domain.admin.dto.AdminResponseDTO
import heispirate.cattower.domain.admin.model.Role
import heispirate.cattower.domain.admin.service.AdminService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AdminController(
    private val adminService: AdminService
) {
    @Operation(summary = "어드민 조회", description = "특정 어드민을 조회합니다.")
    @GetMapping("/admin/{email}")
    fun getAdmin(@PathVariable email: String): ResponseEntity<AdminResponseDTO> {
        return ResponseEntity.ok(adminService.getAdmin(email))
    }

    @Operation(summary = "어드민 목록 조회", description = "어드민 목록을 조회합니다.")
    @GetMapping("/admin")
    fun getAllAdmins(): ResponseEntity<List<AdminResponseDTO>> {
        return ResponseEntity.ok(adminService.getAllAdmin())
    }

    @Operation(summary = "어드민 생성", description = "유저를 어드민으로 승급합니다.")
    @PostMapping("/admin/changeUserToAdmin")
    fun changeUserToAdmin(@RequestBody request: AdminRequestDTOver1): ResponseEntity<AdminResponseDTO> {
        return ResponseEntity.ok(adminService.changeUserToAdminVersion1(request))
    }

    @Operation(summary = "어드민 권한 수정", description = "어드민의 권한을 수정합니다.")
    @PostMapping("/admin/update")
    fun updateAdmin(@RequestParam email: String, @RequestParam newRole: Role): ResponseEntity<AdminResponseDTO> {
        return ResponseEntity.ok(adminService.updateAdmin(email, newRole))
    }

    @Operation(summary = "어드민 권한 삭제", description = "어드민의 권한을 삭제합니다.")
    @DeleteMapping("/admin/delete/{email}")
    fun deleteAdmin(@PathVariable email: String): ResponseEntity<Boolean> {
        return ResponseEntity.ok(adminService.deleteAdmin(email))
    }
}