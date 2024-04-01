package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.example.demo.entity.ProductCategory;

@RepositoryRestResource(collectionResourceRel = "productcategory",path ="product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
