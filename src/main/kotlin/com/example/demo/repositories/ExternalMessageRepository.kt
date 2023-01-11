package com.example.demo.repositories

import com.example.demo.models.entity.ExternalMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExternalMessageRepository: JpaRepository<ExternalMessage, Long> {
    fun findAllByMessageTo(id: Long): List<ExternalMessage>
}