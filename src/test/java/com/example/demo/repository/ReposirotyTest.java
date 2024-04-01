package com.example.demo.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.entity.ProductCategory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReposirotyTest {
	
	@Autowired
	private ProductCategoryRepository categoryRepository;
	
	@Test
	public void test1() {
		ProductCategory category =  new ProductCategory();
		category.setCategoryName("Illustraions");
		
		ProductCategory category2 = this.categoryRepository.save(category);
		
		Assertions.assertThat(category2).isNotNull();
		Assertions.assertThat(category2.getId()).isGreaterThan(1);
		
	}

}
