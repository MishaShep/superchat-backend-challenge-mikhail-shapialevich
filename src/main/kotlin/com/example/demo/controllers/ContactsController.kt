package com.example.demo.controllers

import com.example.demo.models.dto.ContactDto
import com.example.demo.services.ContactService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contacts")
class ContactsController(
    private val contactService: ContactService
) {

    @GetMapping("/all")
    fun getAllContacts(): List<ContactDto> {
        return contactService.getAllContactsList()
    }
}