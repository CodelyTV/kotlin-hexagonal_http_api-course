package com.codely.course.domain

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}
