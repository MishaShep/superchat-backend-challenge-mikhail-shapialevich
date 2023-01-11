package com.example.demo.services.impl

import com.example.demo.models.entity.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepo: UserRepository,
) : UserService {
    override fun getUserIdByUsername(nickname: String): Long {
        return userRepo.getUserByNickname(nickname).id!!
    }

    override fun getFullNameById(id: Long): String {
        val user = userRepo.getUserById(id)
        return "${user.firstName} ${user.lastName}"
    }

    override fun getUserByNickname(nickname: String): User {
        return userRepo.getUserByNickname(nickname)
    }
}