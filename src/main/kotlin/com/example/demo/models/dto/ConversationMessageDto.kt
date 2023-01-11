package com.example.demo.models.dto

import java.time.Instant

data class ConversationMessageDto(
    val messageSender: String,
    val text: String,
    val createdDate: Instant
)
