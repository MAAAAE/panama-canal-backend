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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errorMessage = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "Invalid request"
        val errorResponse = mapOf("errorCode" to HttpStatus.BAD_REQUEST.value().toString(), "errorMessage" to errorMessage)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<String> =
        ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
}