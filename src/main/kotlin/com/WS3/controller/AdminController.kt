package com.WS3.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AdminController {
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
    fun admin(): String {
        return "admin can access this endpoint"
    }
}