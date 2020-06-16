package app.theone.kotlinvue.model.data.json

class OrderJson {
    var orderId: Int? = null
    var userId: Int? = null
    var statusId: Int? = null
    var total: Int? = null
    var orderedProducts: Iterable<Int>? = null

    constructor(userId: Int?, statusId: Int?, total: Int?, orderedProducts: Iterable<Int>?) {
        this.userId = userId
        this.statusId = statusId
        this.total = total
        this.orderedProducts = orderedProducts
    }
}