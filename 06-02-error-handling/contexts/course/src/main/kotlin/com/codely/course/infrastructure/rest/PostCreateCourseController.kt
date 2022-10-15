package com.codely.course.infrastructure.rest

import com.codely.course.application.CourseCreator
import com.codely.course.domain.InvalidCourseIdException
import com.codely.course.domain.InvalidCourseNameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostCreateCourseController(private val courseCreator: CourseCreator) {

    @PostMapping("/course")
    fun execute(
        @RequestBody request: CreateCourseRequest
    ): ResponseEntity<String> {
        return try {
            courseCreator.create(request.id, request.name)
            ResponseEntity.ok().build()
        } catch (exception: Throwable) {
            when (exception) {
                is InvalidCourseIdException,
                is InvalidCourseNameException -> ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(exception.message)

                else -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went bad")
            }
        }
    }
}

data class CreateCourseRequest(
    val id: String = "",
    val name: String = ""
)
