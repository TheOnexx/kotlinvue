package app.theone.kotlinvue.model.data.json

class CommentJson {
    var userId: Int? = null
    var productId: Int? = null
    var content: String? = null

    constructor(userId: Int?, productId: Int?, content: String?) {
        this.userId = userId
        this.productId = productId
        this.content = content
    }
}