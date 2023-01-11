package com.example.demo.controllers

import com.example.demo.models.dto.RegisterExternalServiceDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.ExternalMessage
import com.example.demo.services.ExternalServiceWebHookService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external/service")
class ExternalServiceWebHookController(
    private val service: ExternalServiceWebHookService
) {

    @PostMapping("/register")
    fun registerService(@RequestBody request: RegisterExternalServiceDto): String {
        return service.registerExternalService(request)
    }

    @PostMapping("/{urlCode}/send")
    fun sendMessage(@RequestBody msg: SendMessageDto, @PathVariable urlCode: String): ExternalMessage {
        return service.sendMessage(urlCode, msg)
    }
}