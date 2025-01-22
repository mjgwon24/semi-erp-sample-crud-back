package com.example.semi_erp_sample_crud.board.usecase;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardReadUseCase {
    BoardReadResponse read(BoardReadRequest request); // 특정 게시글 조회
    Page<BoardListResponse> readAll(Pageable pageable); // 페이징된 게시글 목록 조회
}
