package com.example.demo.services

import com.example.demo.models.dto.ContactDto

interface ContactService {
    fun getAllContactsList(): List<ContactDto>
    fun getContactByUserId(id: Long): ContactDto
    fun getContactByUsername(username: String): ContactDto
}