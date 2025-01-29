package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.ProductDTO;
import com.eric.cakemall.exception.ProductNotFoundException;
import com.eric.cakemall.model.Product;
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
                    existingProduct.setProductCategory(productDTO.getProductCategory());
                    existingProduct.setProductStatus(productDTO.getStatus() != null && productDTO.getStatus().equals("上架中") ? 1 : 0);
                    return converToDTO(productRepository.save(existingProduct));
                })
                .orElseThrow(() -> new ProductNotFoundException("找不到商品 ID：" + productNo));
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
