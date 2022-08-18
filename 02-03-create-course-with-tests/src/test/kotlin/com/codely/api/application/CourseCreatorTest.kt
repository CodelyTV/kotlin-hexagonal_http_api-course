package com.codely.api.application

import com.codely.api.BaseTest
import com.codely.api.domain.course.Course
import com.codely.api.domain.course.CourseId
import com.codely.api.domain.course.CourseName
import com.codely.api.domain.course.CourseRepository
import com.codely.api.domain.course.InvalidCourseIdException
import com.codely.api.domain.course.InvalidCourseNameException
import io.mockk.mockk
import io.mockk.verify
import java.time.LocalDateTime
import java.util.UUID
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CourseCreatorTest : BaseTest() {
    private lateinit var courseRepository: CourseRepository
    private lateinit var courseCreator: CourseCreator

    @BeforeEach
    fun setUp() {
        courseRepository = mockk(relaxUnitFun = true)
        courseCreator = CourseCreator(courseRepository)
    }

    @Test
    fun `should create a course successfully`() {
        givenFixedDate(fixedDate)

        courseCreator.create(id, name)

        thenTheCourseShouldBeSaved()
    }

    @Test
    fun `should fail with invalid id`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidCourseIdException> { courseCreator.create("Invalid", name) }
    }

    @Test
    fun `should fail with invalid name`() {
        givenFixedDate(fixedDate)

        assertThrows<InvalidCourseNameException> { courseCreator.create(id, "    ") }
    }

    private fun thenTheCourseShouldBeSaved() {
        verify { courseRepository.save(
            Course(
                id = CourseId(UUID.fromString(id)),
                name = CourseName(name),
                createdAt = fixedDate
            ))
        }
    }

    companion object {
        private const val id = "caebae03-3ee9-4aef-b041-21a400fa1bb7"
        private const val name = "Kotlin Hexagonal Architecture Api Course"
        private val fixedDate = LocalDateTime.parse("2022-08-09T14:50:42")
    }
}
