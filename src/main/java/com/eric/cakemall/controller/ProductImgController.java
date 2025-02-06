package com.eric.cakemall.controller;

import com.eric.cakemall.dto.ProductImgDTO;
import com.eric.cakemall.exception.ProductImgNotFoundException;
import com.eric.cakemall.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product_images")
public class ProductImgController {

    @Autowired
    private ProductImgService productImgService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadProductImage(@RequestParam("file") MultipartFile file, @RequestParam("productNo") Integer productNo) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("上傳的檔案不可為空");
        }

        productImgService.uploadProductImage(file, productNo);

        Map<String, String> response = new HashMap<>();
        response.put("message", "圖片上傳成功");
        return ResponseEntity.ok(response);
    }

    // 取得所有商品圖片
    @GetMapping
    public ResponseEntity<List<ProductImgDTO>> getAllProductImages() {
        return ResponseEntity.ok(productImgService.getAllProductImages());
    }

    // 取得單一圖片
    @GetMapping("/{id}")
    public ResponseEntity<ProductImgDTO> getProductImageById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productImgService.getProductImageById(id));
        } catch (ProductImgNotFoundException ex) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "找不到圖片");
            response.put("message", ex.getMessage());
            return ResponseEntity.ok(productImgService.getProductImageById(id));
        }
    }

    // 刪除圖片
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProductImage(@PathVariable Integer id) {
        try {
            productImgService.deleteProductImage(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "圖片刪除成功");
            return ResponseEntity.ok(response);
        } catch (ProductImgNotFoundException ex) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "找不到圖片");
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}