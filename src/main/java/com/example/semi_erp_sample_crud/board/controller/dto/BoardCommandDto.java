package com.example.semi_erp_sample_crud.board.controller.dto;

import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public final class BoardCommandDto {
    private BoardCommandDto() {}

    public record BoardCreateRequest(
            @NotBlank(message = "제목을 입력해주세요.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            @Size(max = 50, message = "제목은 최대 50글자입니다.")
            String title,

            @NotBlank(message = "본문을 입력해주세요.")
            @Size(min = 3, message = "본문은 세 글자 이상 입력해주세요.")
            String content
    ) {}

    @Builder
    public record BoardCreateResponse(Board board) {}

    @Builder
    public record BoardUpdateRequest(
            @NotNull(message = "수정할 게시글 ID를 입력해주세요.")
            Long id,
            @NotBlank(message = "제목을 입력해주세요.")
            @Size(min = 3, message = "제목은 세 글자 이상 입력하세요.")
            @Size(max = 50, message = "제목은 최대 50글자입니다.")
            String title,
            @NotBlank(message = "본문을 입력해주세요.")
            @Size(min = 3, message = "본문은 세 글자 이상 입력해주세요.")
            String content
    ) {}

    @Builder
    public record BoardUpdateResponse(
            Long id,
            String title,
            String content,
            BoardStatus status,
            int likeCount,
            int dislikeCount,
            String updatedAt
    ) {}

    public record BoardDeleteRequest(
            @NotNull(message = "삭제할 게시글 ID를 입력해주세요.")
            Long id
    ) {}

    @Builder
    public record BoardDeleteResponse(
            Long id,
            String message
    ) {}
}