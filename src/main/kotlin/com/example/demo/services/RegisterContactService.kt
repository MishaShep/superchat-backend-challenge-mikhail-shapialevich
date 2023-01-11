package com.example.demo.services

import com.example.demo.models.dto.ContactDto
import com.example.demo.models.dto.RegisterContactDto

interface RegisterContactService {

    fun registerContact(request: RegisterContactDto): ContactDto
}