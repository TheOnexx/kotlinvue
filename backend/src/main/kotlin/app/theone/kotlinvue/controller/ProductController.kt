package app.theone.kotlinvue.controller

import app.theone.kotlinvue.model.data.json.ProductJson
import app.theone.kotlinvue.model.service.ProductService
import app.theone.kotlinvue.utils.asJsonString
import app.theone.kotlinvue.utils.toDTOList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
        @Autowired val productService: ProductService
) {
    @GetMapping("all")
    fun getAllProducts(): ResponseEntity<*> {
        val allProducts = productService.allProducts()
        val toDTOList = allProducts.toDTOList<ProductJson>()
        return ResponseEntity.ok(toDTOList)
    }
}