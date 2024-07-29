package io.maaaae.panama_canal.dto.specs

import io.maaaae.panama_canal.common.constant.Method
import jakarta.validation.constraints.Size

data class SpecsUpdateRequest(
    @field:Size(max = 255, message = "Endpoint must be less than 255 characters")
    val endpoint: String? = null,
    val method: Method? = null,
    val headers: String? = null
)
