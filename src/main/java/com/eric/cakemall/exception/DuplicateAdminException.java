package com.eric.cakemall.exception;

public class DuplicateAdminException extends RuntimeException {
    public DuplicateAdminException(String message) {
        super(message);
    }
}
