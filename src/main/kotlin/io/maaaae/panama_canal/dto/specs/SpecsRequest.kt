package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.common.constant.Method
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class SpecsRequest(
    @field:NotBlank(message = "Title must not be blank")
    @field:Size(max = 255, message = "Endpoint must be less than 255")
    val name: String,

    @field:NotBlank(message = "Endpoint must not be blank")
    @field:Size(max = 255, message = "Endpoint must be less than 255")
    val endpoint: String,

    val method: Method,

    @field:Positive(message = "Category Id must be positive digit")
    val categoryId: Long,

    @field:NotBlank(message = "Custom Route must not be blank")
    val customRoute: String,

    @field:NotBlank(message = "Headers must not be blank")
    val headers: String
)