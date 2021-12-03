package com.WS3.repository

import com.WS3.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account?, Long?> {
    fun findByUsername(username: String?): Optional<Account?>?
}