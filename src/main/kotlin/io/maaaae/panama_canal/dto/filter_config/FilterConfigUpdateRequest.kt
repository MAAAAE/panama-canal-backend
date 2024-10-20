package io.maaaae.panama_canal.dto.filter_config

data class FilterConfigUpdateRequest(
    val id: Long = -1,
    val filterName: String,
    val param: String,
    val value: String
)