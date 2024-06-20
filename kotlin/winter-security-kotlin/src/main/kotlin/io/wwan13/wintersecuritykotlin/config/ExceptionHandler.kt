package io.wwan13.wintersecuritykotlin.config

import io.wwan13.wintersecurity.exception.forbidden.ForbiddenException
import io.wwan13.wintersecurity.exception.unauthirized.UnauthorizedException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ForbiddenException::class)
    fun handleForbiddenException(
        e: ForbiddenException
    ): ResponseEntity<String> {
        return ResponseEntity.status(e.httpStatusCode).body(e.message)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(
        e: UnauthorizedException
    ): ResponseEntity<String> {
        return ResponseEntity.status(e.httpStatusCode).body(e.message)
    }
}