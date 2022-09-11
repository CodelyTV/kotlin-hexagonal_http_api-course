package com.codely.course.infrastructure.rest.find

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GetFindCourseByIdController() {

    @GetMapping("/course/{id}")
    fun execute(
        @PathVariable id: String
    ): ResponseEntity<String> {
        return try {
            ResponseEntity.ok().body(
                """
                 {
                    "id": "f2fe1e4e-1e8f-493b-ac67-2c88090cae0a",
                    "name": "Saved course",
                    "created_at": "2022-08-31T09:07:36.155Z"
                }
            """.trimIndent()
            )
        } catch (exception: Throwable) {
            ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build()
        }
    }
}

data class CreateCourseRequest(
    val id: String,
    val name: String
)
