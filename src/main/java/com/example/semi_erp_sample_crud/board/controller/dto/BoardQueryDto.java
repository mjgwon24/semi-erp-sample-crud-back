package com.example.semi_erp_sample_crud.board.controller.dto;

import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import lombok.Builder;

public final class BoardQueryDto {
    private BoardQueryDto() {}

    @Builder
    public record BoardReadRequest(
            //dto to entity
            Long id,                            // 입력해도 되고 안해도 됨
            String title,                       // 조회 조건으로 사용
            String content  // 선택적 조건
    ) {}

    @Builder
    public record BoardReadResponse(
            Long id,
            String title,
            String content,
            BoardStatus status,
            int likeCount,
            int dislikeCount,
            String createdAt,           // instant - string 변환
            String updatedAt            // instant - string 변환// entity to dto

    ) {}

    @Builder
    public record BoardListResponse(
            Long id,
            String title,
            String content
    ) {}

}
