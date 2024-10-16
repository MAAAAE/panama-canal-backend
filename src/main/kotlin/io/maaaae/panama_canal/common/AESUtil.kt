package io.maaaae.panama_canal.common

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object AESUtil {

    private const val KEY_ENV_VAR = "ENCRYPTION_KEY"
    private val secretKey = getSecretKeySpec()
    private const val algorithm = "AES/CBC/PKCS5Padding"
    private const val keyAlgorithm = "PBKDF2WithHmacSHA256"
    private const val keyLength = 256
    private const val iterationCount = 65536
    private fun getSecretKeySpec(): String {
        val key = System.getenv(KEY_ENV_VAR) ?: throw IllegalArgumentException("The encryption key must be set in the environment variable $KEY_ENV_VAR")
        require(key.length == 16) { "The encryption key must be 16 characters long." }
        return key
    }

    fun encrypt(data: String): String {
        val salt = generateSalt()
        val iv = generateIv()
        val secretKey = generateSecretKey(salt)

        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))
        val encryptedBytes = cipher.doFinal(data.toByteArray())

        val combined = salt + iv + encryptedBytes
        return Base64.getEncoder().encodeToString(combined)
    }


    fun decrypt(encryptedData: String): String {
        val combined = Base64.getDecoder().decode(encryptedData)

        val salt = combined.slice(0 until 16).toByteArray()
        val iv = combined.slice(16 until 32).toByteArray()
        val encrypted = combined.slice(32 until combined.size).toByteArray()

        val secretKey = generateSecretKey(salt)

        val cipher = Cipher.getInstance(algorithm)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        val decryptedBytes = cipher.doFinal(encrypted)
        return String(decryptedBytes)
    }

    private fun generateSalt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    private fun generateIv(): ByteArray {
        val random = SecureRandom()
        val iv = ByteArray(16)
        random.nextBytes(iv)
        return iv
    }

    private fun generateSecretKey(salt: ByteArray): SecretKeySpec {
        val factory = SecretKeyFactory.getInstance(keyAlgorithm)
        val spec = PBEKeySpec(secretKey.toCharArray(), salt, iterationCount, keyLength)
        val tmp = factory.generateSecret(spec)
        return SecretKeySpec(tmp.encoded, "AES")
    }

}