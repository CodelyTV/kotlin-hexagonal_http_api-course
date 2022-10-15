package com.codely.course.infrastructure.rest

import com.codely.course.application.CourseCreator
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostCreateCourseController(private val courseCreator: CourseCreator) {

    @PostMapping("/course")
    fun execute(
        @RequestBody request: CreateCourseRequest
    ) {
        courseCreator.create(request.id, request.name)
    }
}

data class CreateCourseRequest(
    val id: String,
    val name: String
)
