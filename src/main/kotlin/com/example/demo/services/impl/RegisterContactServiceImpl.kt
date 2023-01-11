package com.example.demo.services.impl

import com.example.demo.models.dto.ContactDto
import com.example.demo.models.dto.RegisterContactDto
import com.example.demo.models.entity.User
import com.example.demo.repositories.UserRepository
import com.example.demo.services.RegisterContactService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class RegisterContactServiceImpl(
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : RegisterContactService {

    override fun registerContact(request: RegisterContactDto): ContactDto {
        if (request.password != request.passwordConfirm)
            throw AuthenticationException("password and password confirm do not match")
        val user = userRepo.save(
            User(
                nickname = request.username,
                firstName = request.firstName,
                lastName = request.lastName,
                userPassword = passwordEncoder.encode(request.password),
                email = request.email,
                role = "USER"
            )
        )
        return ContactDto(
            id = user.id!!,
            nickname = user.nickname,
            fullName = "${user.firstName} ${user.lastName}",
            email = user.email
        )
    }
}