package com.example.semi_erp_sample_crud.board.usecase;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto;
import com.example.semi_erp_sample_crud.board.entity.Board;

public interface BoardUpdateUseCase {
    Board update(Long id, BoardCommandDto.BoardUpdateRequest request);
}
