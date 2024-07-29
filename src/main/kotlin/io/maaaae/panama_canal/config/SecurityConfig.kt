package io.maaaae.panama_canal.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
          .authorizeHttpRequests { exchanges ->
              exchanges
                  .requestMatchers("/actuator/**").permitAll()  // Actuator 엔드포인트는 공개
                  .requestMatchers("/specs/**").permitAll() // Debug
                  .anyRequest().permitAll()  // 그 외 모든 요청은 인증 필요
          }
          .oauth2ResourceServer {
              it.jwt(Customizer.withDefaults())
          }  // JWT를 통한 OAuth2 리소스 서버 설정

        return http.build()
    }
}
