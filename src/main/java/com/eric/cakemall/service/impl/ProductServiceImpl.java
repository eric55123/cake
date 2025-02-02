package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.ProductDTO;
import com.eric.cakemall.exception.CategoryNotFoundException;
import com.eric.cakemall.exception.ProductNotFoundException;
import com.eric.cakemall.model.Product;
import com.eric.cakemall.model.ProductCategory;
import com.eric.cakemall.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eric.cakemall.repository.ProductRepository;
import com.eric.cakemall.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = converToEntity(productDTO);

        //新增商品紀錄時間
        if (product.getProductNo() == null) {
            product.setProductAddTime(LocalDateTime.now());
        }

        Product savedProduct = productRepository.save(product);
        return converToDTO(savedProduct);
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> converToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Integer productNo) {
        return productRepository.findById(productNo)
                .map(product -> converToDTO(product));
    }

    @Override
    public ProductDTO updateProduct(Integer productNo, ProductDTO productDTO) {
        // 檢查其他商品是否已經有相同名稱
        Optional<Product> existingProductWithSameName = productRepository.findByProductName(productDTO.getProductName());
        if (existingProductWithSameName.isPresent() && !existingProductWithSameName.get().getProductNo().equals(productNo)) {
            // 商品名稱已存在，拋出例外
            throw new ProductNotFoundException("商品名稱已存在: " + productDTO.getProductName());
        }

        return productRepository.findById(productNo)
                .map(existingProduct -> {
                    existingProduct.setProductName(productDTO.getProductName());
                    existingProduct.setProductDesc(productDTO.getProductDesc());
                    existingProduct.setProductAddQty(productDTO.getProductAddQty());
                    existingProduct.setRemainingQty(productDTO.getRemainingQty());
                    existingProduct.setProductPrice(productDTO.getProductPrice());

                    // 查詢並設定 ProductCategory
                    ProductCategory category = productCategoryRepository
                            .findByProductCategoryName(productDTO.getProductCategory())
                            .orElseThrow(() -> new CategoryNotFoundException("找不到商品類別：" + productDTO.getProductCategory()));
                    existingProduct.setProductCategory(category);

                    // 設定商品狀態
                    existingProduct.setProductStatus("上架中".equals(productDTO.getStatus()) ? 1 : 0);

                    return converToDTO(productRepository.save(existingProduct));
                })
                .orElseThrow(() -> new ProductNotFoundException("找不到商品 ID：" + productNo));
    }


    @Override
    public void deleteProduct(Integer productNo) {
        productRepository.deleteById(productNo);
    }

    // DTO 與實體的轉換
    private ProductDTO converToDTO(Product savedProduct) {
        ProductDTO dto = new ProductDTO();
        dto.setProductNo(savedProduct.getProductNo());
        dto.setProductName(savedProduct.getProductName());
        dto.setProductDesc(savedProduct.getProductDesc());
        dto.setProductAddQty(savedProduct.getProductAddQty());
        dto.setRemainingQty(savedProduct.getRemainingQty());
        dto.setProductPrice(savedProduct.getProductPrice());

        // 獲取 ProductCategory 的名稱
        if (savedProduct.getProductCategory() != null) {
            dto.setProductCategory(savedProduct.getProductCategory().getProductCategoryName());
        }

        dto.setStatus(savedProduct.getProductStatus() == 1 ? "上架中" : "已下架");
        return dto;
    }


    private Product converToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductNo(productDTO.getProductNo());
        product.setProductName(productDTO.getProductName());
        product.setProductDesc(productDTO.getProductDesc());
        product.setProductAddQty(productDTO.getProductAddQty());
        product.setRemainingQty(productDTO.getRemainingQty());
        product.setProductPrice(productDTO.getProductPrice());

        if (productDTO.getProductCategory() != null) {
            ProductCategory category = productCategoryRepository
                    .findByProductCategoryName(productDTO.getProductCategory())
                    .orElseThrow(() -> new CategoryNotFoundException("找不到商品類別：" + productDTO.getProductCategory()));
            product.setProductCategory(category);
        }

        return product;
    }

}