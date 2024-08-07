package io.maaaae.panama_canal.common

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class AESUtilTest : BehaviorSpec({

    given("an AESUtil") {
        `when`("encrypting the same message twice") {
            val input = "secret message"
            then("it should produce different outputs") {
                val encrypted1 = AESUtil.encrypt(input)
                val encrypted2 = AESUtil.encrypt(input)
                encrypted1 shouldNotBe encrypted2
            }
        }

        `when`("encrypting and then decrypting a message") {
            val input = "another secret message"
            then("it should return the original message") {
                val encrypted = AESUtil.encrypt(input)
                val decrypted = AESUtil.decrypt(encrypted)
                decrypted shouldBe input
            }
        }

        `when`("encrypting and decrypting an empty string") {
            val input = ""
            then("it should work correctly") {
                val encrypted = AESUtil.encrypt(input)
                val decrypted = AESUtil.decrypt(encrypted)
                decrypted shouldBe input
            }
        }

        `when`("encrypting and decrypting a long string") {
            val input = "A".repeat(1000)
            then("it should work correctly") {
                val encrypted = AESUtil.encrypt(input)
                val decrypted = AESUtil.decrypt(encrypted)
                decrypted shouldBe input
            }
        }

        `when`("encrypting and decrypting a string with special characters") {
            val input = "!@#$%^&*()_+{}|:<>?~`-=[]\\;',./"
            then("it should work correctly") {
                val encrypted = AESUtil.encrypt(input)
                val decrypted = AESUtil.decrypt(encrypted)
                decrypted shouldBe input
            }
        }

        `when`("attempting to decrypt an invalid input") {
            val invalidInput = "not a valid encrypted string"
            then("it should fail") {
                runCatching {
                    AESUtil.decrypt(invalidInput)
                }.isFailure shouldBe true
            }
        }
    }
})
