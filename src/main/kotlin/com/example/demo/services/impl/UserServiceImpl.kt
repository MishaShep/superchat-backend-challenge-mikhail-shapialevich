package com.example.demo.services.impl

import com.example.demo.models.entity.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.core.userdetails.User as SecurityUser

@Service
class UserServiceImpl(
    private val userRepo: UserRepository,
) : UserDetailsService, UserService {

    override fun getFullNameById(id: Long): String {
        val user = userRepo.getUserById(id)
        return "${user.firstName} ${user.lastName}"
    }

    override fun getUserByNickname(nickname: String): User {
        return userRepo.getUserByNickname(nickname)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val appUser = userRepo.getUserByNickname(username!!)
        return SecurityUser.builder()
            .username(appUser.nickname)
            .password(appUser.userPassword)
            .roles(appUser.role)
            .build()
    }
}