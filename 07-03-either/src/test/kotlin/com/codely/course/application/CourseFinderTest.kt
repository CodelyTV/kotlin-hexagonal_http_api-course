package com.codely.course.application

import com.codely.course.application.find.CourseFinder
import com.codely.course.application.find.CourseResponse
import com.codely.course.domain.CourseMother
import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseNotFoundException
import com.codely.course.domain.course.CourseRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import kotlin.test.assertEquals

class CourseFinderTest {

    private lateinit var courseRepository: CourseRepository
    private lateinit var courseFinder: CourseFinder

    @BeforeEach
    internal fun setUp() {
        courseRepository = mockk()
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

        val actualResult = `when the finder is executed`()

        `then the result is a failure with no found exception`(actualResult)
    }

    private fun `then the result is a failure with no found exception`(actualResult: Result<CourseResponse>) {
        val expected = Result.failure<CourseResponse>(
            CourseNotFoundException(courseId)
        )
        assertEquals(expected, actualResult)
    }

    private fun `given no course is saved`() {
        val result = Result.failure<Course>(
            CourseNotFoundException(courseId)
        )
        every { courseRepository.find(courseId) } returns result
    }

    private fun `then the found course is equals to expected`(actualCourse: Result<CourseResponse>) {
        val expected = Result.success(
            CourseResponse(
                id = courseId.value.toString(),
                name = courseName,
                createdAt = courseCreatedAt
            )
        )

        assertEquals(expected, actualCourse)
    }

    private fun `when the finder is executed`(): Result<CourseResponse> {
        return courseFinder.execute(courseId.value.toString())
    }

    private fun `given an saved course`() {

        val course = CourseMother.sample(
            id = courseId,
            name = courseName,
            createdAt = courseCreatedAt
        )
        val result = Result.success(course)


        every { courseRepository.find(course.id) } returns result
    }

    companion object {
        private val courseId = CourseId.fromString("7ab75530-5da7-4b4a-b083-a779dd6c759e")
        private const val courseName = "Course Finder Test Name"
        private val courseCreatedAt = LocalDateTime.parse("2022-08-31T09:00:00")
    }
}
