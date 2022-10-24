package com.codely.course.infrastructure

import com.codely.course.application.CourseCreator
import com.codely.course.domain.InvalidCourseIdException
import com.codely.course.domain.InvalidCourseNameException
import com.codely.course.infrastructure.rest.CreateCourseRequest
import com.codely.course.infrastructure.rest.PostCreateCourseController
import io.mockk.every
import io.mockk.mockk
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class PostCreateCourseControllerTest {

    private lateinit var courseCreator: CourseCreator
    private lateinit var controller: PostCreateCourseController

    @BeforeEach
    fun setUp() {
        courseCreator = mockk()
        controller = PostCreateCourseController(courseCreator)
    }

    @Test
    fun `should return a successfull response`() {
        every { courseCreator.create(any(), any()) } returns Unit

        val response = controller.execute(CreateCourseRequest("03ef970b-719d-49c5-8d80-7dc762fe4be6", "Test"))

        assertEquals(ResponseEntity.ok().build(), response)
    }

    @Test
    fun `should fail when id is not valid`() {
        every { courseCreator.create(any(), any()) } throws InvalidCourseIdException("1", null)

        val response = controller.execute(CreateCourseRequest("1", "Test"))

        assertEquals(ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("The course id is not valid"), response)
    }

    @Test
    fun `should fail when name is not valid`() {
        every { courseCreator.create(any(), any()) } throws InvalidCourseNameException("Invalid")

        val response = controller.execute(CreateCourseRequest("03ef970b-719d-49c5-8d80-7dc762fe4be6", "Invalid"))

        assertEquals(ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("The course name is not valid"), response)
    }

    @Test
    fun `should fail when there is an uncontrolled exception`() {
        every { courseCreator.create(any(), any()) } throws Throwable()

        val response = controller.execute(CreateCourseRequest("03ef970b-719d-49c5-8d80-7dc762fe4be6", "Test"))

        assertEquals(ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .build(), response)
    }
}
