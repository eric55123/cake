package com.eric.cakemall.service;





import com.eric.cakemall.dto.ProductCategoryDTO;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {

    // 新增商品類別
    ProductCategoryDTO createCategory(ProductCategoryDTO categoryDTO);

    // 取得所有商品類別
    List<ProductCategoryDTO> getAllCategories();

    // 根據 ID 取得商品類別
    Optional<ProductCategoryDTO> getCategoryById(Integer categoryId);

    // 更新商品類別
    ProductCategoryDTO updateCategory(Integer categoryId, ProductCategoryDTO categoryDTO);

    // 刪除商品類別
    void deleteCategory(Integer categoryId);


}
