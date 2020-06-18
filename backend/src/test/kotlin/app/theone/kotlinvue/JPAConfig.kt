package app.theone.kotlinvue

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.orm.jpa.JpaTransactionManager
import javax.persistence.EntityManagerFactory
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.apache.tomcat.jni.SSL.setPassword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(basePackages = ["app.theone.kotlinvue.model.repository"])
@ComponentScan("app.theone.kotlinvue.*")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
class JPAConfig {

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(env.getProperty("hibernate.connection.driver_class")!!)
        dataSource.url = env.getProperty("hibernate.connection.url")
        dataSource.username = env.getProperty("hibernate.connection.username")
        dataSource.password = env.getProperty("jdbc.password")

        return dataSource
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("app.theone.kotlinvue.model")
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        em.setJpaProperties(additionalProperties())
        return em
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }

    fun additionalProperties(): Properties {
        val properties = Properties()
        properties.load(javaClass.classLoader.getResourceAsStream("hibernate.properties"))
        return properties
    }

}