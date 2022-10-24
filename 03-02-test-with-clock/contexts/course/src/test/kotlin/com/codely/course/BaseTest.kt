package com.codely.course

import com.codely.course.domain.Clock
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import java.time.LocalDateTime
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

open class BaseTest {
    protected lateinit var clock: Clock

    @BeforeEach
    protected fun initClock() {
        clock = mockk()
    }

    protected fun givenFixedDate(fixedDatetime: LocalDateTime) {
        every {
            clock.now()
        } returns fixedDatetime
    }

    @AfterEach
    protected fun cleanMock() {
        unmockkAll()
    }
}
