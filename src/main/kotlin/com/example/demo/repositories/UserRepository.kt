package com.example.demo.repositories

import com.example.demo.models.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun getUserByNickname(nickname: String): User
    fun getUserById(id: Long): User
}