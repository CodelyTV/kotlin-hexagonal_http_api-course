package com.codely.shared

import com.codely.course.domain.Clock
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import java.time.LocalDateTime
import org.junit.jupiter.api.AfterEach

open class BaseTest {
    protected lateinit var clock: Clock

    protected fun givenFixedDate(fixedDatetime: LocalDateTime) {
       every { clock.now() } returns fixedDatetime
    }

    @AfterEach
    protected fun cleanMock() {
        unmockkAll()
    }
}
