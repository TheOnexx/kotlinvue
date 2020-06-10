package app.theone.kotlinvue.model.service.impl

import app.theone.kotlinvue.model.data.jpa.Category
import app.theone.kotlinvue.model.data.jpa.Product
import app.theone.kotlinvue.model.data.json.CategoryJson
import app.theone.kotlinvue.model.exception.CategoryNotFoundException
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.service.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
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

    override fun removeCategory(categoryId: Int) {
        val possibleCategory = categoryRepository.findById(categoryId)
        val category = possibleCategory.orElseThrow {
            throw CategoryNotFoundException(categoryId)
        }
        categoryRepository.delete(category)
    }

    override fun getProductsInCategory(categoryId: Int): Iterable<Product> {
        val possibleCategory = categoryRepository.findById(categoryId)
        val category = possibleCategory.orElseThrow {
            throw CategoryNotFoundException(categoryId)
        }

        return category.products
    }

    private fun findParentCategory(parentId: Int?): Category? {
        parentId?.let {parentIdSafe ->
            val possibleParentCategory = categoryRepository.findById(parentIdSafe)
            if(possibleParentCategory.isPresent) {
                return possibleParentCategory.get()
            }

        }
        return null
    }
}