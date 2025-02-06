package com.eric.cakemall.service.impl;

import com.eric.cakemall.dto.ProductImgDTO;
import com.eric.cakemall.exception.ProductImgNotFoundException;
import com.eric.cakemall.model.Product;
import com.eric.cakemall.model.ProductImg;
import com.eric.cakemall.repository.ProductImgRepository;
import com.eric.cakemall.repository.ProductRepository;
import com.eric.cakemall.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImgServiceImpl implements ProductImgService {

    @Autowired
    private ProductImgRepository productImgRepository;

    @Autowired
    private ProductRepository productRepository;

    private final String uploadDir = "C:\\Users\\user\\Desktop\\mall\\product_images\\";

    @Override
    public ProductImgDTO uploadProductImage(MultipartFile file, Integer productNo) {
        // 檢查商品是否存在
        Product product = productRepository.findById(productNo)
                .orElseThrow(() -> new RuntimeException("找不到指定的產品 ID：" + productNo));

        // 儲存圖片到目錄
        String filePath = uploadDir + file.getOriginalFilename();
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("檔案上傳失敗", e);
        }

        // 儲存圖片資訊到資料庫
        ProductImg productImg = new ProductImg();
        productImg.setProduct(product);
        productImg.setProductImgUrl(filePath);
        ProductImg savedProductImg = productImgRepository.save(productImg);

        return new ProductImgDTO(savedProductImg.getProductImgNo(), savedProductImg.getProduct().getProductNo(), savedProductImg.getProductImgUrl());
    }

    @Override
    public List<ProductImgDTO> getAllProductImages() {
        return productImgRepository.findAll().stream()
                .map(img -> new ProductImgDTO(img.getProductImgNo(), img.getProduct().getProductNo(), img.getProductImgUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductImgDTO getProductImageById(Integer productImgNo) {
        ProductImg productImg = productImgRepository.findById(productImgNo)
                .orElseThrow(() -> new ProductImgNotFoundException("找不到 ID 為 " + productImgNo + " 的圖片"));
        return new ProductImgDTO(productImg.getProductImgNo(), productImg.getProduct().getProductNo(), productImg.getProductImgUrl());
    }

    @Override
    public void deleteProductImage(Integer productImgNo) {
        ProductImg productImg = productImgRepository.findById(productImgNo)
                .orElseThrow(() -> new ProductImgNotFoundException("找不到 ID 為 " + productImgNo + " 的圖片"));

        // 刪除檔案
        String filePath = productImg.getProductImgUrl();
        File file = new File(filePath);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new RuntimeException("無法刪除圖片檔案：" + filePath);
            }
        } else {
            System.out.println("圖片檔案不存在或非檔案：" + filePath);
        }

        // 刪除資料庫紀錄
        productImgRepository.deleteById(productImgNo);
    }
}
