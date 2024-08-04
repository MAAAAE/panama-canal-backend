package io.maaaae.panama_canal.controller.advice

import io.maaaae.panama_canal.common.dto.PanamaErrorResponse
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<PanamaErrorResponse> {
        val errorMessage = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "Invalid request"
        val errorResponse = createErrorResponse(
            errorCode = HttpStatus.BAD_REQUEST.value().toString(),
            errorMessage = errorMessage
        )
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<PanamaErrorResponse> =
        ResponseEntity(
            createErrorResponse(
                errorCode = HttpStatus.NOT_FOUND.value().toString(),
                errorMessage = ex.message,
            ),
            HttpStatus.NOT_FOUND
        )

    private fun createErrorResponse(errorCode: String, errorMessage: String?): PanamaErrorResponse {
        return PanamaErrorResponse(
            errorCode = errorCode,
            errorMessage = errorMessage,
        )
    }
}