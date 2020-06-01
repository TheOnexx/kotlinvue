package app.theone.kotlinvue.controller

import app.theone.kotlinvue.model.data.Message
import app.theone.kotlinvue.model.data.jpa.*
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/api")
class HelloWorld {

    var ids: AtomicInteger = AtomicInteger()
    var currentMessage: String = ""

    @GetMapping("/helloworld")
    fun greet(): String {
        var role = Role(0, "")
        return "helloworld!"
    }

    @GetMapping("/message")
    fun getMessage() = Message(ids.incrementAndGet(), currentMessage)

    @PostMapping("/message")
    fun setMessage(@RequestBody messageObject: Message) {
        currentMessage = messageObject.message + " from the server"
    }

}