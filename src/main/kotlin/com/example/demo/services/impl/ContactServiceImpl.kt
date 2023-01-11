package com.example.demo.services.impl

import com.example.demo.models.dto.ContactDto
import com.example.demo.repositories.UserRepository
import com.example.demo.services.ContactService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(
    private val userRepo: UserRepository
) : ContactService {

    override fun getAllContactsList(): List<ContactDto> {
        return userRepo.findAll()
            .filter { it.nickname != SecurityContextHolder.getContext().authentication.name }
            .map { user ->
                ContactDto(
                    id = user.id!!,
                    nickname = user.nickname,
                    fullName = user.getFullName(),
                    email = user.email
                )
            }
    }

    override fun getContactByUserId(id: Long): ContactDto {
        return userRepo.getUserById(id).let {
            ContactDto(
                id = it.id!!,
                nickname = it.nickname,
                fullName = it.getFullName(),
                email = it.email
            )
        }
    }

    override fun getContactByUsername(username: String): ContactDto {
        return userRepo.getUserByNickname(username).let {
            ContactDto(
                id = it.id!!,
                nickname = it.nickname,
                fullName = it.getFullName(),
                email = it.email
            )
        }
    }

}