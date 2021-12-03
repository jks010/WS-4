package com.WS3.security

import com.WS3.model.Privilege
import com.WS3.model.Role
import com.WS3.repository.AccountRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional
class CustomUserDetailsService(private val accountRepository: AccountRepository) : UserDetailsService {
    //@Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val accountByUsername = accountRepository.findByUsername(username)
        if (!accountByUsername!!.isPresent) {
            throw UsernameNotFoundException("User $username not found.")
        }
        val account = accountByUsername.get()
        if (account.roles == null || account.roles!!.isEmpty()) {
            throw UsernameNotFoundException("User not authorized.")
        }
        return User(
            account.username,
            account.password,
            account.isEnabled,
            true,
            true,
            true,
            getAuthorities(account.roles)
        )
    }

    private fun getAuthorities(roles: Collection<Role>?): MutableList<SimpleGrantedAuthority>? {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: Collection<Role>?): MutableList<String?>? {
        val privileges: List<Privilege> = roles!!.stream()
            .map(Role::privileges)
            .flatMap { obj: Set<Privilege?> -> obj.stream() }
            .collect(Collectors.toList()) as List<Privilege>
        return privileges.stream()
            .map(Privilege::name)
            .collect(Collectors.toList())
    }

    private fun getGrantedAuthorities(privileges: MutableList<String?>?): MutableList<SimpleGrantedAuthority>? {
        return privileges?.stream()
            ?.map { role: String? ->
                SimpleGrantedAuthority(
                    role
                )
            }
            ?.collect(Collectors.toList())
    }
}