package com.example.semi_erp_sample_crud.common.jpa.exeption.response;

import lombok.Builder;

@Builder
public record ApiErrorResponse(
        int status,
        String code,
        String message
) {}
