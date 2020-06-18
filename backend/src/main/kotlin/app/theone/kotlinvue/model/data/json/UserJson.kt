package app.theone.kotlinvue.model.data.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class UserJson : Serializable {

    @JsonProperty("userId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var userId: Int? = null

    @JsonProperty("userName")
    var userName: String? = null

    @JsonProperty("email")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var email: String? = null

    @JsonProperty("password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var password: String? = null

    @JsonProperty("roleId")
    @JsonInclude(JsonInclude.Include.NON_NULL)
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