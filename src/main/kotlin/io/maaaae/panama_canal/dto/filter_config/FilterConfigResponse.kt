package io.maaaae.panama_canal.dto.filter_config

data class FilterConfigResponse(
    val id: Long,
    val filterName: String,
    val param: String,
    val value: String,
)
