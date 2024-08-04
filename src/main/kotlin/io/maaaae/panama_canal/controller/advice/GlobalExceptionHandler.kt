package io.maaaae.panama_canal.controller.advice

import io.maaaae.panama_canal.common.dto.PanamaErrorResponse
import io.maaaae.panama_canal.common.exception.ResourceNotFoundException
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<PanamaErrorResponse> {
        val errorMessage = ex.bindingResult.allErrors.firstOrNull()?.defaultMessage ?: "Invalid request"
        return createErrorResponseEntity(
            errorStatus = HttpStatus.BAD_REQUEST,
            errorMessage = errorMessage
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<PanamaErrorResponse> =
        createErrorResponseEntity(
            errorStatus = HttpStatus.BAD_REQUEST,
            errorMessage = ex.message
        )

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<PanamaErrorResponse> =
        createErrorResponseEntity(
            errorStatus = HttpStatus.NOT_FOUND,
            errorMessage = ex.message
        )


    private fun createErrorResponseEntity(errorStatus: HttpStatus, errorMessage: String?): ResponseEntity<PanamaErrorResponse> =
        ResponseEntity(
            PanamaErrorResponse(
                errorCode = errorStatus.value().toString(),
                errorMessage = errorMessage,
            ),
            errorStatus
        )
}
