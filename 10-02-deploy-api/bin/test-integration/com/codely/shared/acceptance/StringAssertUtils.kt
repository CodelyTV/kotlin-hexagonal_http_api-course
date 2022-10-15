package com.codely.shared.acceptance

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.test.assertEquals

fun String.isEqualToJson(expected: String) {
    val objectMapper = jacksonObjectMapper()
    assertEquals(objectMapper.readTree(expected), objectMapper.readTree(this))
}
