package com.codely.course.application

import com.codely.course.application.find.CourseFinder
import com.codely.course.application.find.CourseResponse
import com.codely.course.domain.CourseMother
import com.codely.course.domain.course.CourseError
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseNotFoundError
import com.codely.course.domain.course.CourseRepository
import com.codely.shared.common.Either
import com.codely.shared.common.Left
import com.codely.shared.common.Right
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import kotlin.test.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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

    private fun `then the result is a failure with no found exception`(actualResult: Either<CourseError, CourseResponse>) {
        val expected = Left<CourseError>(
            CourseNotFoundError(courseId)
        )
        assertEquals(expected, actualResult)
    }

    private fun `given no course is saved`() {
        every { courseRepository.find(courseId) } returns Left(
            CourseNotFoundError(courseId)
        )
    }

    private fun `then the found course is equals to expected`(actualCourse: Either<CourseError, CourseResponse>) {
        val expected = Right<CourseResponse>(
            CourseResponse(
                id = courseId.value.toString(),
                name = courseName,
                createdAt = courseCreatedAt
            )
        )

        assertEquals(expected, actualCourse)
    }

    private fun `when the finder is executed`(): Either<CourseError, CourseResponse> {
        return courseFinder.execute(courseId.value.toString())
    }

    private fun `given an saved course`() {

        val course = CourseMother.sample(
            id = courseId,
            name = courseName,
            createdAt = courseCreatedAt
        )

        every { courseRepository.find(course.id) } returns Right(course)
    }

    companion object {
        private val courseId = CourseId.fromString("7ab75530-5da7-4b4a-b083-a779dd6c759e")
        private const val courseName = "Course Finder Test Name"
        private val courseCreatedAt = LocalDateTime.parse("2022-08-31T09:00:00")
    }
}
