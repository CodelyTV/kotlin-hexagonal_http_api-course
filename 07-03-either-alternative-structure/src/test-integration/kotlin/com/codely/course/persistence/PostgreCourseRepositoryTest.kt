package com.codely.course.persistence

import com.codely.common.Left
import com.codely.common.Right
import com.codely.course.domain.Course
import com.codely.course.domain.CourseCannotBeFoundError
import com.codely.course.domain.CourseId
import com.codely.course.domain.CourseName
import com.codely.course.domain.CourseNotFoundError
import com.codely.course.infrastructure.persistence.PostgreCourseRepository
import com.codely.shared.persistence.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime
import kotlin.test.assertEquals

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostgreCourseRepositoryTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var repository: PostgreCourseRepository

    @Test
    fun `should save a course`() {
        val courseId = "13590efb-c181-4c5f-9f95-b768abde13e2"
        val courseToSave = Course(CourseId.fromString(courseId), CourseName("Test"), LocalDateTime.now())
        repository.save(courseToSave)

        val courseFromDb = repository.find(CourseId.fromString(courseId))

        assertEquals(Right(courseToSave), courseFromDb)
    }

    @Test
    fun `should fail when course is not found`() {
        val courseId = "13590efb-c181-4c5f-9f95-b768abde13e2"

        val courseFromDb = repository.find(CourseId.fromString(courseId))

        assertEquals(Left(CourseNotFoundError(CourseId.fromString(courseId))), courseFromDb)
    }
}
