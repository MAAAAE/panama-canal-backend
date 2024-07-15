package io.maaaae.panama_canal.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers("/actuator/**").permitAll()  // Actuator 엔드포인트는 공개
                    .anyExchange().authenticated()  // 그 외 모든 요청은 인증 필요
            }
            .oauth2ResourceServer {
                it.jwt(Customizer.withDefaults())
            }  // JWT를 통한 OAuth2 리소스 서버 설정
        return http.build()
    }

//    @Bean
//    fun jwtDecoder(): ReactiveJwtDecoder {
//        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:18080/realms/myrealm")
//    }
}