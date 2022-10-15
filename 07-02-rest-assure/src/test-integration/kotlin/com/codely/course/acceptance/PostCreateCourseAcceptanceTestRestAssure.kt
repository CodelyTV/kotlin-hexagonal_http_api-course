package com.codely.course.acceptance

import com.codely.shared.acceptance.BaseAcceptanceTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.Collections
import kotlin.test.assertTrue

class PostCreateCourseAcceptanceTestRestAssure : BaseAcceptanceTest() {

    @Test
    fun `should create a course successfully`() {
        val response = Given {
            contentType(ContentType.JSON)
            body(
                """
               {
                    "id": "97fa5af4-bd81-45d5-974f-d5a3970af252",
                    "name": "Test Acceptance"
               }     
                """
            )
        } When {
            post("/course")
        } Then {
            statusCode(HttpStatus.CREATED.value())
        } Extract {
            body().asString()
        }

        assertTrue(response.isEmpty())
    }
}
