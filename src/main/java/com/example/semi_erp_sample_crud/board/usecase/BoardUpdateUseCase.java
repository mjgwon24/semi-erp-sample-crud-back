package com.example.semi_erp_sample_crud.board.usecase;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;

public interface BoardUpdateUseCase {
    Board update(BoardUpdateRequest request);
}