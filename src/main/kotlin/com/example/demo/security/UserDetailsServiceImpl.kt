package com.example.demo.security

import com.example.demo.repositories.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val appUser = userRepository.getUserByNickname(username!!)
        return User.builder()
            .username(appUser.nickname)
            .password(appUser.userPassword)
            .roles(appUser.role)
            .build()
    }
}