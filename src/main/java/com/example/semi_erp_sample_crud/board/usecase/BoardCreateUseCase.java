package com.example.semi_erp_sample_crud.board.usecase;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;

public interface BoardCreateUseCase {
    Board create(BoardCreateRequest request);
    Board update(BoardUpdateRequest request);
}
