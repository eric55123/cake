package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.ProductDTO;
import com.eric.cakemall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eric.cakemall.repository.ProductRepository;
import com.eric.cakemall.service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

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
        return List.of();
    }

    @Override
    public Optional<ProductDTO> getProductById(Integer productNo) {
        return Optional.empty();
    }

    @Override
    public ProductDTO removeProduct(Integer productNo) {
        return null;
    }

    @Override
    public void deleteProduct(Integer productNo) {

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
        dto.setProductCategory(savedProduct.getProductCategory());
        dto.setStatus(savedProduct.getProductStatus() == 1 ? "上架中" : "已下架");
        return dto;
    }

    private Product converToEntity(ProductDTO ProductDTO) {
        Product product = new Product();
        product.setProductNo(ProductDTO.getProductNo());
        product.setProductName(ProductDTO.getProductName());
        product.setProductDesc(ProductDTO.getProductDesc());
        product.setProductAddQty(ProductDTO.getProductAddQty());
        product.setRemainingQty(ProductDTO.getRemainingQty());
        product.setProductPrice(ProductDTO.getProductPrice());
        product.setProductCategory(ProductDTO.getProductCategory());
        return product;
    }
}
