package io.maaaae.panama_canal.repository.filter_config

import io.maaaae.panama_canal.domain.FilterConfig
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FilterConfigRepository: JpaRepository<FilterConfig, Long> {
}