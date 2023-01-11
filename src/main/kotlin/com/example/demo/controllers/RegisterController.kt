package com.example.demo.controllers

import com.example.demo.models.dto.ContactDto
import com.example.demo.models.dto.RegisterContactDto
import com.example.demo.services.RegisterContactService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterController(
    private val registerService: RegisterContactService
) {

    @PostMapping("/register")
    fun sendMessage(@RequestBody request: RegisterContactDto): ContactDto {
        return registerService.registerContact(request)
    }
}