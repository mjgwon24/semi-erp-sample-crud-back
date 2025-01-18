package com.example.semi_erp_sample_crud.board.usecase;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadResponse;

public interface BoardReadUseCase {
    BoardReadResponse read(BoardReadRequest request);
}
