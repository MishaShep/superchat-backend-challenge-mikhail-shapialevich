package com.example.demo.models.dto

data class ConversationsDto(
    val internalConversations: List<ConversationDto>,
    val externalConversations: List<ExternalConversationDto>
)