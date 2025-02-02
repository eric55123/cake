package com.eric.cakemall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        response.put("errorCode", HttpStatus.NOT_FOUND.value());  // 使用 404 狀態碼作為錯誤代碼
        // 回傳 404 狀態碼
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    // 處理重複類別名稱例外
    @ExceptionHandler(DuplicateCategoryException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateCategoryException(DuplicateCategoryException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Duplicate Category");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 處理找不到資源的例外
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Category Not Found");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DuplicateMemberException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateMemberException(DuplicateMemberException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "重複輸入錯誤");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);  // 409 Conflict
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "參數錯誤");
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(response);  // 400 Bad Request
    }

    @ExceptionHandler(DuplicateAdminException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateAdminException(DuplicateAdminException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("success", "false");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);  // 回傳 409 狀態碼
    }

    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleAdminNotFoundException(AdminNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("success", "false");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 回傳 404 錯誤
    }


}