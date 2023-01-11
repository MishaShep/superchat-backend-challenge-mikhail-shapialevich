package com.example.demo.models.dto

import java.time.Instant

data class ConversationDto(
    val contact: ContactDto,
    val lastMessageDate: Instant,
    val messages: List<ConversationMessageDto>
)
