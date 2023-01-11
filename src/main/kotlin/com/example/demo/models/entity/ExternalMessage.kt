package com.example.demo.models.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@Entity
@Table(name = "external_messages")
@EntityListeners(AuditingEntityListener::class)
data class ExternalMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var text: String,
    var messageFrom: String,
    var messageTo: Long,
    @CreatedDate
    var createdDate: Instant? = null
)
