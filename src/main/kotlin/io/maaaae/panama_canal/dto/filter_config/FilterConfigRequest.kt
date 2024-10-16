package io.maaaae.panama_canal.dto.filter_config

data class FilterConfigRequest(
    val filterName: String,
    val param: String,
    val value: String
)
