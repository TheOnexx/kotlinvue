package app.theone.kotlinvue.model.service.impl

import app.theone.kotlinvue.model.data.jpa.Category
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.json.CategoryJson
import app.theone.kotlinvue.model.exception.CategoryNotFoundException
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired

class CategoryServiceImpl(
        @Autowired val categoryRepository: CategoryRepository
) : CategoryService {

    override fun addCategory(categoryJson: CategoryJson): Category {
        val parentCategory = findParentCategory(categoryJson.parentId)
        val newCategory = Category(
                0,
                categoryJson.name!!,
                categoryJson.description!!,
                parentCategory
        )
        parentCategory?.addSubCategory(newCategory)

        return categoryRepository.save(newCategory)
    }

    override fun editCategory(categoryJson: CategoryJson): Category {
        val possibleCategory = categoryRepository.findById(categoryJson.categoryId!!)
        val category = possibleCategory.orElseThrow {
            throw CategoryNotFoundException(categoryJson.categoryId!!)
        }
        val parentCategory = findParentCategory(categoryJson.parentId)

        category.update(categoryJson, parentCategory)

        return categoryRepository.save(category)

    }

    override fun removeCategory(categoryJson: CategoryJson): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProductsInCategory(categoryId: Int): Iterable<Product> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun findParentCategory(parentId: Int?): Category? {
        parentId?.let {parentIdSafe ->
            val possibleParentCategory = categoryRepository.findById(parentIdSafe)
            possibleParentCategory.ifPresent {
                return@ifPresent
            }

        }
        return null
    }
}