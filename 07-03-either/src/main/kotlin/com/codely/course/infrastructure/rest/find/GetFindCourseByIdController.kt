package com.codely.course.infrastructure.rest.find

import com.codely.course.application.find.CourseFinder
import com.codely.course.application.find.CourseResponse
import com.codely.course.domain.course.CourseNotFoundError
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetFindCourseByIdController(private val courseFinder: CourseFinder) {

    @GetMapping("/course/{id}")
    fun execute(
        @PathVariable id: String
    ): ResponseEntity<CourseResponse> = courseFinder.execute(id).fold(
        ifRight = {
            ResponseEntity.ok().body(it)
        },
        ifLeft = {
            when (it) {
                is CourseNotFoundError ->
                    ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build()

                else ->
                    ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
            }
        }
    )
}
