package com.example.demo.controllers

import com.example.demo.models.dto.ConversationsDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.Message
import com.example.demo.services.MessagesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/messages")
class MessagesController(
    private val messagesService: MessagesService
) {

    @GetMapping("/all")
    fun getAllMessages(): ConversationsDto {
        return messagesService.getAllMessages()
    }

    @PostMapping("/send")
    fun sendMessage(@RequestBody request: SendMessageDto): Message {
        return messagesService.sendNewMessage(request)
    }
}
