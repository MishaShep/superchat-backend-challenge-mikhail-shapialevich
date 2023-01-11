package com.example.demo.models.dto

data class RegisterContactDto(
    val username: String,
    val password: String,
    val passwordConfirm: String,
    val email: String,
    val firstName: String,
    val lastName: String
)