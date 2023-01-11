package com.example.demo.models.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
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
    @Column(name = "message_to")
    var messageToId: Long,
    @CreatedDate
    var createdDate: Instant? = null
)
