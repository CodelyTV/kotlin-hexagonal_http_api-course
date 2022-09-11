package course.acceptance

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import com.codely.course.infrastructure.persistence.PostgreCourseRepository
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
import shared.acceptance.BaseAcceptanceTest
import shared.acceptance.isEqualToJson

class GetFindCourseAcceptanceTestRestAssure : BaseAcceptanceTest() {

    @Autowired
    private lateinit var courseRepository: PostgreCourseRepository

    @Test
    @Sql("classpath:db/fixtures/find/add-course-data.sql")
    fun `should find course successfully with fixture`() {
        When {
            get("/course/${course.id}")
        } Then {
            statusCode(HttpStatus.OK.value())
        } Extract {
            body().asString().isEqualToJson(expectedCourseResponse)
        }
    }

    @Test
    fun `should find course successfully with course creation`() {
        val response = Given {
            `an existin course`()
            contentType(ContentType.JSON)
            body("")
        } When {
            get("/course/${course.id}")
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
        private val course = Course(
            CourseId.fromString("f2fe1e4e-1e8f-493b-ac67-2c88090cae0a"),
            CourseName("Saved course"),
            now
        )
        private val expectedCourseResponse = """
                {
                    "id": "f2fe1e4e-1e8f-493b-ac67-2c88090cae0a",
                    "name": "Saved course",
                    "created_at": "2022-08-31T09:07:36.155Z"
                }
            """.trimIndent()
    }
}
