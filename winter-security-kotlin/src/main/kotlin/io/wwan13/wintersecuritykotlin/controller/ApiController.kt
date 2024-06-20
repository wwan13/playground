package io.wwan13.wintersecuritykotlin.controller

import io.wwan13.wintersecurity.resolve.RequestUserId
import io.wwan13.wintersecurity.resolve.RequestUserRoles
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping("/id")
    fun getUserId(
        @RequestUserId id: Long,
        @RequestUserRoles role: String,
    ): ResponseEntity<String> {
        return ResponseEntity.ok().body("$id + $role")
    }

    @GetMapping("/admin")
    fun adminApi(
        @RequestUserId id: Long
    ): ResponseEntity<String> {
        return ResponseEntity.ok().body("$id")
    }
}
