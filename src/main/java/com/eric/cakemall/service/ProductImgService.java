package com.eric.cakemall.service;

import com.eric.cakemall.dto.ProductImgDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImgService {
    ProductImgDTO uploadProductImage(MultipartFile file, Integer productNo);
    List<ProductImgDTO> getAllProductImages();
    ProductImgDTO getProductImageById(Integer productImgNo);
    void deleteProductImage(Integer productImgNo);
}
