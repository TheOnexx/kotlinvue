package app.theone.kotlinvue.utils

import com.fasterxml.jackson.databind.ObjectMapper

fun <T> T.asJsonString(): String {
    val objectMapper = ObjectMapper()
    val writer = objectMapper.writerWithDefaultPrettyPrinter()
    return writer.writeValueAsString(this)

}