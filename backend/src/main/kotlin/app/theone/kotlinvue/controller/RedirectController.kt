package app.theone.kotlinvue.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping



@Controller
class RedirectController : ErrorController {
    override fun getErrorPath() = "/error"

    @RequestMapping("/error")
    fun error(): String {
        return "forward:/"
    }

}