package com.sda.online_store_final_project;

import com.sda.online_store_final_project.entity.Category;
import com.sda.online_store_final_project.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OnlineStoreFinalProjectApplicationTests {

	@Autowired
	CategoryRepository categoryRepository;

	//TestCategory
	@Test
	public void testCategory() {
		//init data <begin>
		Category category = new Category();
		category.setCategoryName("newCategory");
		//init data <end>

		//getAllCategory()
		List<Category> list = categoryRepository.findAll();
		System.out.println(list.isEmpty());
//		//addCategory() <for create>
		category = categoryRepository.save(category);
//		//updateCategory() <for update>
//		category.setCategoryName("updateNewCate");
//		category = categoryRepository.save(category);
//		//getCategoryById()
//        Optional<Category> optionalCategory = this.categoryRepository.findById(category.getCategoryId());
//        category = (Category) optionalCategory.get();
//		//removeCategoryById()
//        this.categoryRepository.deleteById(category.getCategoryId());
	}

}
