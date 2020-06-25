package app.theone.kotlinvue.model.data.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class UserJson : Serializable {

    @JsonProperty("userId")
    var userId: Int? = null

    @JsonProperty("userName")
    var userName: String? = null

    @JsonProperty("email")
    var email: String? = null

    @JsonProperty("password")
    var password: String? = null

    @JsonProperty("roleId")
    var role: Int? = null


    constructor(userId: Int?, userName: String?, email: String?, password: String?, role: Int?)  {
        this.userId = userId
        this.userName = userName
        this.email = email
        this.password = password
        this.role = role
    }

    constructor(){}

    companion object {

        private const val serialVersionUID = -1764970284520387975L
    }

}