package course.acceptance

import com.codely.course.domain.course.Course
import com.codely.course.domain.course.CourseId
import com.codely.course.domain.course.CourseName
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.UUID
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import shared.acceptance.BaseAcceptanceTest

class PostCreateCourseAcceptanceTest : BaseAcceptanceTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Test
    fun `should create a course successfully`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
               {
                    "id": "97fa5af4-bd81-45d5-974f-d5a3970af252",
                    "name": "Test Acceptance"
               }     
           """.trimIndent()
                )
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect {
                assertEquals("", it.response.contentAsString)
            }

        val courseFromDb = jdbcTemplate.queryForObject(
            "select * from course where id=?",
            mapRow(),
            "97fa5af4-bd81-45d5-974f-d5a3970af252"
        )

        assertEquals(
            Course(
                CourseId.fromString("97fa5af4-bd81-45d5-974f-d5a3970af252"),
                CourseName("Test Acceptance"),
                LocalDateTime.now()
            ), courseFromDb
        )
    }

    private fun mapRow(): RowMapper<Course> {
        return RowMapper { rs: ResultSet, _: Int ->
            val id = CourseId(UUID.fromString(rs.getString("id")))
            val name = CourseName(rs.getString("name"))
            val createdAt = rs.getTimestamp("created_at").toLocalDateTime()
            Course(id, name, createdAt)
        }
    }
}
