package app.theone.kotlinvue.model.data.json

class CategoryJson {
    var categoryId: Int? = null
    var name: String? = null
    var description: String? = null
    var parentId: Int? = null

    constructor(name: String?, description: String?, parentId: Int?) {
        this.name = name
        this.description = description
        this.parentId = parentId
    }
}