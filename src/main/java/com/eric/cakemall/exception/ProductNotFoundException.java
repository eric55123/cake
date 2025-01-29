package com.eric.cakemall.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends RuntimeException {
    private HttpStatus status;

    public ProductNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;  // 預設 HTTP 404 狀態
    }

    public ProductNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }


}