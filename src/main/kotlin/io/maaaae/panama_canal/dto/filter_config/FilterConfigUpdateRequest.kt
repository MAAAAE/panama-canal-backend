package io.maaaae.panama_canal.dto.filter_config

data class FilterConfigUpdateRequest(
    val id: Long,
    val filterName: String? = null,
    val param: String? = null,
    val value: String? = null
)