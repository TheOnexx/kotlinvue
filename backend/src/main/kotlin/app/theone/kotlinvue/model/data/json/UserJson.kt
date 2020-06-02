package app.theone.kotlinvue.model.data.json

class UserJson {

    var userId: Int? = null
    var userName: String? = null
    var email: String? = null
    var password: String? = null
    var role: Int? = null

    constructor(userId: Int?, userName: String?, email: String?, password: String?, role: Int?) {
        this.userId = userId
        this.userName = userName
        this.email = email
        this.password = password
        this.role = role
    }
}