package com.example.semi_erp_sample_crud.board.repository.projection;

import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import lombok.Builder;

import java.time.Instant;

public class BoardProjection {
    public record BoardListProjection(
            Long id,
            String title,
            BoardStatus status,
            Instant createdAt
    ) {}
    @Builder
    public record BoardDetailProjection(
            Long id,
            String title,
            String content,
            BoardStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {}
    @Builder
    public record BoardSimpleProjection(
            Long id,
            String title,
            String content,
            Instant updatedAt
    ) {}
}
