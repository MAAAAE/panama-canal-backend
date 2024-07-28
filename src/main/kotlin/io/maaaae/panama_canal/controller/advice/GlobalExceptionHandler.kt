package io.maaaae.panama_canal.controller.advice

import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleValidationExceptions(ex: WebExchangeBindException): ResponseEntity<Map<String, String?>> {
        val errors = mutableMapOf<String, String?>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        }
        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
}