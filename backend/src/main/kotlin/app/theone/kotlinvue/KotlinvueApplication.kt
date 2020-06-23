package app.theone.kotlinvue

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories("app.theone.kotlinvue.model.repository")
class KotlinvueApplication {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<KotlinvueApplication>(*args)
        }
    }

}
