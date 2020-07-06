package app.theone.kotlinvue.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper

fun <T> T.asJsonString(): String {
    val objectMapper = ObjectMapper()
    val writer = objectMapper.writerWithDefaultPrettyPrinter()
    return writer.writeValueAsString(this)

}

inline fun <reified R> Iterable<*>.toDTOList(): Iterable<R> {
    val modelMapper = ModelMapper()
    return this.map {
        modelMapper.map(it, R::class.java)
    }
}