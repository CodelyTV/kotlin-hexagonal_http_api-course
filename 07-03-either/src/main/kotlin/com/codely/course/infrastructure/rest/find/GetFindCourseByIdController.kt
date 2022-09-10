package com.codely.course.infrastructure.rest.find

import com.codely.course.application.find.CourseFinder
import com.codely.course.application.find.CourseResponse
import com.codely.course.domain.course.CourseNotFoundError
import com.codely.shared.common.Either
import com.codely.shared.common.Left
import com.codely.shared.common.Right
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
    ): Either<ResponseEntity<String>, ResponseEntity<CourseResponse>> = courseFinder.execute(id).flatMap(
        rightF = {
            Right(ResponseEntity.ok().body(it))
        },
        leftF = {
            when (it) {
                is CourseNotFoundError -> Left(
                    ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(it.message)
                )

                else -> Left(
                    ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .build()
                )
            }
        }
    )
}
