package com.codely.course.application

import com.codely.common.course.CourseMother
import com.codely.course.BaseTest
import com.codely.course.application.find.CourseFinder
import com.codely.course.application.find.CourseResponse
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseNotFoundException
import com.codely.course.domain.CourseRepository
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CourseFinderTest : BaseTest() {

    private lateinit var courseRepository: CourseRepository
    private lateinit var courseFinder: CourseFinder

    @BeforeEach
    internal fun setUp() {
        courseRepository = mockk(relaxUnitFun = true)
        courseFinder = CourseFinder(courseRepository)
    }

    @Test
    fun `should find an existing course`() {
        `given an saved course`()

        val actualCourse = `when the finder is executed`()

        `then the found course is equals to expected`(actualCourse)
    }

    @Test
    fun `should throw an exception when course is not found`() {
        `given no course is saved`()

        assertThrows<CourseNotFoundException> { `when the finder is executed`() }
    }

    private fun `given no course is saved`() {
        every { courseRepository.find(any()) } throws CourseNotFoundException(CourseId.fromString(courseId))
    }

    private fun `then the found course is equals to expected`(actualCourse: CourseResponse) {
        val expected = CourseResponse(
            id = courseId,
            name = courseName,
            createdAt = courseCreatedAt
        )

        assertEquals(expected, actualCourse)
    }

    private fun `when the finder is executed`(): CourseResponse {
        return courseFinder.execute(courseId)
    }

    private fun `given an saved course`() {
        every { courseRepository.find(any()) } returns CourseMother.sample(
            id = courseId,
            name = courseName,
            createdAt = courseCreatedAt
        )
    }

    companion object {
        private const val courseId = "7ab75530-5da7-4b4a-b083-a779dd6c759e"
        private const val courseName = "Course Finder Test Name"
        private val courseCreatedAt = LocalDateTime.parse("2022-08-31T09:00:00")
    }
}
