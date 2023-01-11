package com.example.demo.services.impl

import com.example.demo.models.dto.RegisterExternalServiceDto
import com.example.demo.models.dto.SendMessageDto
import com.example.demo.models.entity.ExternalMessage
import com.example.demo.models.entity.ExternalService
import com.example.demo.repositories.ExternalServiceRepository
import com.example.demo.services.ExternalServiceWebHookService
import com.example.demo.services.MessagesService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ExternalServiceWebHookServiceImpl(
    val externalServiceRepository: ExternalServiceRepository,
    val messagesService: MessagesService
) : ExternalServiceWebHookService {

    @Value("\${webhook.host.url}")
    lateinit var hostUrl: String

    override fun registerExternalService(request: RegisterExternalServiceDto): String {
        val service = externalServiceRepository.save(
            ExternalService(
                serviceName = request.name,
                urlCode = UUID.randomUUID().toString()
            )
        )
        return "$hostUrl/${service.urlCode}/send"
    }

    override fun sendMessage(urlCode: String, msg: SendMessageDto): ExternalMessage {
        val externalService = externalServiceRepository.findExternalServiceByUrlCode(urlCode)
        return messagesService.sendMessageFromExternalSource(msg, externalService.serviceName)
    }
}