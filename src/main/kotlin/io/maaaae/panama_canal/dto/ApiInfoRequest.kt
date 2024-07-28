package io.maaaae.panama_canal.dto

import io.maaaae.panama_canal.common.constant.Method
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ApiInfoRequest(
    @field:NotBlank(message = "Endpoint must not be blank")
    @field:Size(max = 255, message = "Endpoint must be less than 255")
    val endpoint: String,
    @field:NotNull(message = "Method must not be null")
    val method: Method?,
    // TODO: need more parameters
)