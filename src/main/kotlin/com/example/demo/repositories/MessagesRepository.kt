package com.example.demo.repositories

import com.example.demo.models.entity.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessagesRepository: JpaRepository<Message, Long> {
    fun findAllByMessageFromIdOrMessageToId(messageFrom: Long, messageTo: Long): List<Message>
}