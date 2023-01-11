package com.example.demo.services

import com.example.demo.models.dto.ConversationsDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.ExternalMessage
import com.example.demo.models.entity.Message

interface MessagesService {

    fun getAllMessages(): ConversationsDto

    fun sendNewMessage(request: SendMessageDto): Message

    fun sendMessageFromExternalSource(request: SendMessageDto, serviceName: String): ExternalMessage
}