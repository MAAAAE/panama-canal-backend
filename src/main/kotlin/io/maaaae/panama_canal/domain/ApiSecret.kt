package io.maaaae.panama_canal.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "api_secret")
data class ApiSecret(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val secretId: Long = 0,
    @ManyToOne @JoinColumn(name = "api_id")
    val api: ApiInfo,
    val secretKey: String,
    val description: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)