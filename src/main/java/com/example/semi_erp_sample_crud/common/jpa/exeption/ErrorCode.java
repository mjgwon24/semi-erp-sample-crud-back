package com.example.semi_erp_sample_crud.common.jpa.exeption;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name(); // enum default method
    String defaultMessage();
    HttpStatus defaultHttpStatus();
    RuntimeException defaultException();
    RuntimeException defaultException(Throwable cause);
}
