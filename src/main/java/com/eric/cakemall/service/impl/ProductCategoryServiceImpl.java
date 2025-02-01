package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.ProductCategoryDTO;
import com.eric.cakemall.exception.DuplicateCategoryException;
import com.eric.cakemall.model.ProductCategory;
import com.eric.cakemall.repository.ProductCategoryRepository;
import com.eric.cakemall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategoryDTO createCategory(ProductCategoryDTO categoryDTO) {
        //檢查稱是否重復
        if (categoryRepository.findByProductCategoryName(categoryDTO.getProductCategoryName()).isPresent()) {
            throw new DuplicateCategoryException("商品類別名稱已存在：" + categoryDTO.getProductCategoryName());
        }
        ProductCategory category = convertToEntity(categoryDTO);
        ProductCategory savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public List<ProductCategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductCategoryDTO> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).map(this::convertToDTO);
    }

    @Override
    public ProductCategoryDTO updateCategory(Integer categoryId, ProductCategoryDTO categoryDTO) {
        // 檢查名稱是否重複，但排除目前正在更新的類別
        Optional<ProductCategory> existingCategoryWithSameName = categoryRepository.findByProductCategoryName(categoryDTO.getProductCategoryName());
        if (existingCategoryWithSameName.isPresent() && !existingCategoryWithSameName.get().getProductCategoryId().equals(categoryId)) {
            throw new DuplicateCategoryException("商品類別名稱已存在：" + categoryDTO.getProductCategoryName());
        }

        return categoryRepository.findById(categoryId)
                .map(existingCategory -> {
                    existingCategory.setProductCategoryName(categoryDTO.getProductCategoryName());
                    return convertToDTO(categoryRepository.save(existingCategory));
                })
                .orElseThrow(() -> new DuplicateCategoryException("找不到類別 ID：" + categoryId));
    }


    @Override
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }


    private ProductCategoryDTO convertToDTO(ProductCategory category) {
        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setProductCategoryId(category.getProductCategoryId());
        dto.setProductCategoryName(category.getProductCategoryName());
        return dto;
    }

    private ProductCategory convertToEntity(ProductCategoryDTO dto) {
        ProductCategory category = new ProductCategory();
        category.setProductCategoryId(dto.getProductCategoryId());
        category.setProductCategoryName(dto.getProductCategoryName());
        return category;
    }
}
