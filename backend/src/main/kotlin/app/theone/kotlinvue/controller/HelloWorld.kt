package app.theone.kotlinvue.controller

import app.theone.kotlinvue.model.data.Message
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/api")
class HelloWorld {

    var ids: AtomicInteger = AtomicInteger()
    var currentMessage: String = ""

    @GetMapping("/helloworld")
    fun greet(): String {
        return "helloworld!"
    }

    @GetMapping("/message")
    fun getMessage() = Message(ids.incrementAndGet(), currentMessage)

    @PostMapping("/message")
    fun setMessage(@RequestBody messageObject: Message) {
        currentMessage = messageObject.message + " from the server"
    }

}