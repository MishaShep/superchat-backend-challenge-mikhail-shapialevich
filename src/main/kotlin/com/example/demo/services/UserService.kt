package com.example.demo.services

import com.example.demo.models.entity.User

interface UserService {

    fun getFullNameById(id: Long): String
    fun getUserByNickname(nickname: String): User
}