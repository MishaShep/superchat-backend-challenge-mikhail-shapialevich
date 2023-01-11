package com.example.demo.models.dto

import java.time.Instant

data class ExternalConversationDto(
    val sender: String,
    val lastMessageDate: Instant,
    val messages: List<ConversationMessageDto>
)
