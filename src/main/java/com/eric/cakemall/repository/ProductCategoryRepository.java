package com.eric.cakemall.repository;

import com.eric.cakemall.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    Optional<ProductCategory> findByProductCategoryName(String productCategoryName);
}
