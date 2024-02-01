package com.raphaelsilva.credit.system.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionsDetails> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName] = messageError
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionsDetails(title = "Bad Request! Consult the documentation",
                                        timestamp = LocalDateTime.now(), status = HttpStatus.BAD_REQUEST.value(),
                                        exception = ex.javaClass.toString(), details = errors))
    }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex: DataAccessException): ResponseEntity<ExceptionsDetails> {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ExceptionsDetails(title = "Conflict Request! Consult the documentation",
                                        timestamp = LocalDateTime.now(), status = HttpStatus.CONFLICT.value(),
                                        exception = ex.javaClass.toString(),
                                        details = mutableMapOf(ex.cause.toString() to ex.message)))
    }

    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(ex: BusinessException): ResponseEntity<ExceptionsDetails> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ExceptionsDetails(title = "Bad Request! Consult the documentation",
                                        timestamp = LocalDateTime.now(), status = HttpStatus.BAD_REQUEST.value(),
                                        exception = ex.javaClass.toString(),
                                        details = mutableMapOf(ex.cause.toString() to ex.message)))
    }
}