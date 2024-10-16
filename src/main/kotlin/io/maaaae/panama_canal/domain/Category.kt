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
    var secretKey: String,
    @Column(nullable = false)
    var secretValue: String,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var secretType: SecretType

) {

    @PrePersist
    @PreUpdate
    fun encryptSecret() {
        secretValue.let {
            secretValue = AESUtil.encrypt(it)
        }
    }

    @PostLoad
    fun decryptSecret() {
        secretValue.let {
            secretValue = AESUtil.decrypt(it)
        }
    }

    fun getDecryptedPassword(): String {
        return secretValue
    }

    fun update(categoryRequest: CategoryRequest) {
        categoryRequest.name?.let { name = it }
        categoryRequest.description?.let { description = it }
        categoryRequest.domain?.let { domain = it }
        categoryRequest.secretValue?.let { secretValue = it }
        categoryRequest.secretKey?.let { secretKey = it }
        categoryRequest.secretType?.let { secretType = it }
    }
}

enum class SecretType {
    PARAMETER,
    HEADER,
    NONE
}
