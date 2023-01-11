package com.example.demo.services

import com.example.demo.models.dto.RegisterExternalServiceDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.ExternalMessage

interface ExternalServiceWebHookService {

    fun registerExternalService(request: RegisterExternalServiceDto): String
    fun sendMessage(urlCode: String, msg: SendMessageDto): ExternalMessage
}