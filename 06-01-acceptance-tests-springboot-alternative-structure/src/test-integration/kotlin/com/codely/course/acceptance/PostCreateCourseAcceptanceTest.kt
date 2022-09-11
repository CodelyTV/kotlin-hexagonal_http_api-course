package com.codely.course.acceptance

import com.codely.shared.acceptance.BaseAcceptanceTest
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PostCreateCourseAcceptanceTest : BaseAcceptanceTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

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
    }
}
