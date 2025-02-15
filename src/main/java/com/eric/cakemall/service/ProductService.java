package com.eric.cakemall.service;

import com.eric.cakemall.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> getProductById(Integer productNo);

    ProductDTO updateProduct(Integer productNo, ProductDTO productDTO);

    void deleteProduct(Integer productNo);
}
