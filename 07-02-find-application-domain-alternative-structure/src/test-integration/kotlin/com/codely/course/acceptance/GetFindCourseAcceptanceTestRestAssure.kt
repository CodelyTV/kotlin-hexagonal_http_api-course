package com.codely.course.acceptance

import com.codely.common.course.CourseMother
import com.codely.course.infrastructure.persistence.PostgreCourseRepository
import com.codely.shared.acceptance.BaseAcceptanceTest
import com.codely.shared.acceptance.isEqualToJson
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import java.time.LocalDateTime
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql

class GetFindCourseAcceptanceTestRestAssure : BaseAcceptanceTest() {

    @Autowired
    private lateinit var courseRepository: PostgreCourseRepository

    @Test
    @Sql("classpath:db/fixtures/find/add-course-data.sql")
    fun `should find course successfully with fixture`() {
        When {
            get("/course/${course.id.value}")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            body().asString().isEqualToJson(expectedCourseResponse)
        }
    }

    @Test
    fun `should find course successfully with course creation`() {
        Given {
            `an existin course`()
            contentType(ContentType.JSON)
            body("")
        } When {
            get("/course/${course.id.value}")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            body().asString().isEqualToJson(expectedCourseResponse)
        }
    }

    private fun `an existin course`() {
        courseRepository.save(course)
    }

    companion object {
        private val now = LocalDateTime.parse("2022-08-31T09:07:36")
        private val course = CourseMother.sample(
            id = "f2fe1e4e-1e8f-493b-ac67-2c88090cae0a",
            name = "Saved course",
            createdAt = now
        )
        private val expectedCourseResponse = """
                {
                    "id": "${course.id.value}",
                    "name": "${course.name.value}",
                    "createdAt": "$now"
                }
            """.trimIndent()
    }
}
