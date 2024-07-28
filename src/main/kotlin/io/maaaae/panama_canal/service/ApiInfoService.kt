package io.maaaae.panama_canal.service

import io.maaaae.panama_canal.dto.ApiInfoDto

interface ApiInfoService {
    fun getAllApiInfo(): List<ApiInfoDto>
}