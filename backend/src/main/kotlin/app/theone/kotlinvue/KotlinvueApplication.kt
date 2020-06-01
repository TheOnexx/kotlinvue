package app.theone.kotlinvue

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.persistence.EntityManager

@SpringBootApplication
@EnableJpaRepositories("app.theone.kotlinvue.model.repository")
class KotlinvueApplication {

    fun main(args: Array<String>) {
        runApplication<KotlinvueApplication>(*args)
    }
    @EventListener(classes = [ApplicationReadyEvent::class])
    fun testHibernate(
            @Autowired
            entityManager: EntityManager
    ) {


    }

}
