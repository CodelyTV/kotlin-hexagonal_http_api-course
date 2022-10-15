package com.codely.course.infrastructure.rest

import com.codely.course.domain.InvalidCourseIdException
import com.codely.course.domain.InvalidCourseNameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

// @ControllerAdvice
class PostCreateCourseControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [IllegalArgumentException::class, InvalidCourseIdException::class, InvalidCourseNameException::class])
    protected fun handleMissingArgument(
        ex: RuntimeException,
        request: WebRequest
    ): ResponseEntity<String> = ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(ex.message)
}
