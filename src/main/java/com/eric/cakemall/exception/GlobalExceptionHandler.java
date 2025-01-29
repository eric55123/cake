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
        response.put("errorCode", 404);  // 自訂錯誤代碼或保持 404

        // 可以選擇回傳 200 OK 或保持 404 狀態
        return new ResponseEntity<>(response, HttpStatus.OK);  // 使用 OK 來表示請求成功，但回應告知查無商品
    }
}