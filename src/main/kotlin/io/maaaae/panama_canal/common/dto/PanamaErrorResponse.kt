package io.maaaae.panama_canal.common.dto

data class PanamaErrorResponse(
    val errorCode: String,
    val errorMessage: String? = "",
)
