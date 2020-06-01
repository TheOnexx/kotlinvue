package app.theone.kotlinvue.model.data.jpa

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name="statuses")
data class Status (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val statusId: Int,

        val value: String
)

