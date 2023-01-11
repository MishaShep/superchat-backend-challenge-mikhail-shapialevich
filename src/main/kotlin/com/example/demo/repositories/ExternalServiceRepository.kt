package com.example.demo.repositories

import com.example.demo.models.entity.ExternalService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExternalServiceRepository : JpaRepository<ExternalService, Long> {

    fun findExternalServiceByUrlCode(urlCode: String): ExternalService
}