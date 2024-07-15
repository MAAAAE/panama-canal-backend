package io.maaaae.panama_canal.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/test")
    fun hello(): String = "hello"
}