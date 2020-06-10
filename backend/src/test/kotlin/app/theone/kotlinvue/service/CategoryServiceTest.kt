package app.theone.kotlinvue.service

import app.theone.kotlinvue.JPAConfig
import app.theone.kotlinvue.model.data.json.CategoryJson
import app.theone.kotlinvue.model.repository.CategoryRepository
import app.theone.kotlinvue.model.service.CategoryService
import app.theone.kotlinvue.model.service.ProductService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.support.AnnotationConfigContextLoader

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(
        classes = [JPAConfig::class],
        loader = AnnotationConfigContextLoader::class)
class CategoryServiceTest : AbstractTransactionalJUnit4SpringContextTests(){
    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var categoryService: CategoryService

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    fun givenCategoryInfo_whenSaved_thenCategoryAddedToDB(){
        val categoryJson = buildCategory()

        Assert.assertEquals(0, categoryRepository.findAll().count())

        categoryService.addCategory(categoryJson)

        Assert.assertEquals(1, categoryRepository.findAll().count())
    }

    @Test
    fun givenParentAndChildCategory_whenSaved_thenHierarchySaved() {
        val categoryParent = buildCategory()
        val addedCategory = categoryService.addCategory(categoryParent)
        val categoryChild = buildCategory()
        categoryChild.parentId = addedCategory.categoryId


        val addedChild = categoryService.addCategory(categoryChild)


        Assert.assertEquals(addedCategory.categoryId, addedChild.parent?.categoryId)
        Assert.assertEquals(addedChild.parent?.categoryId, addedCategory.categoryId)
    }

    @Test
    fun givenCategory_whenRemoved_thenCategoryRemoved() {
        val categoryJson = buildCategory()

        val addCategory = categoryService.addCategory(categoryJson)

        val id = addCategory.categoryId

        categoryService.removeCategory(id)

        val findById = categoryRepository.findById(id)

        Assert.assertEquals(false, findById.isPresent)


    }

    private fun buildCategory(): CategoryJson {
        return CategoryJson(
                "categoryName",
                "categoryDescription",
                null
        )
    }
}