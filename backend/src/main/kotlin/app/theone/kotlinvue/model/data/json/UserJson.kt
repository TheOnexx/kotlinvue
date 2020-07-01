package app.theone.kotlinvue.model.data.json

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class UserJson : Serializable {

    @JsonProperty("userId")
    var userId: Int? = null

    @JsonProperty("username")
    var username: String? = null

    @JsonProperty("email")
    var email: String? = null

    @JsonProperty("password")
    var password: String? = null

    @JsonProperty("roleId")
    var role: String? = null


    constructor(userId: Int?, userName: String?, email: String?, password: String?, role: String?)  {
        this.userId = userId
        this.username = userName
        this.email = email
        this.password = password
        this.role = role
    }

    constructor(){}

    companion object {

        private const val serialVersionUID = -1764970284520387975L
    }

}