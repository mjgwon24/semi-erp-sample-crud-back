package com.example.semi_erp_sample_crud.board.usecase;


import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardDeleteRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;

public interface BoardDeleteUseCase {
    void delete(BoardDeleteRequest request);
    void hardDelete(BoardDeleteRequest request);
}
