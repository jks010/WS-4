package com.WS3.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('PRIVILEGE_USER_READ')")
    fun user(): String {
        return "user can access this endpoint"
    }

    @GetMapping("/hello")
    fun hey(): String {
        return "Hello \n"
    }
}