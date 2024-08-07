package io.maaaae.panama_canal.domain

import io.maaaae.panama_canal.common.AESUtil
import io.maaaae.panama_canal.dto.category.CategoryRequest
import jakarta.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val categoryId: Long = 0,
    @Column(unique = true)
    var name: String,
    var description: String?,
    @Column(nullable = false)
    var domain: String,
    @Column(nullable = false)
    var secret: String,

    ) {

    @PrePersist
    @PreUpdate
    fun encryptSecret() {
        secret.let {
            secret = AESUtil.encrypt(it)
        }
    }

    @PostLoad
    fun decryptSecret() {
        secret.let {
            secret = AESUtil.decrypt(it)
        }
    }

    fun getDecryptedPassword(): String {
        return secret
    }

    fun update(categoryRequest: CategoryRequest) {
        categoryRequest.name?.let { name = it }
        categoryRequest.description?.let { description = it }
        categoryRequest.domain?.let { domain = it }
        categoryRequest.secret?.let { secret = it }
    }
}