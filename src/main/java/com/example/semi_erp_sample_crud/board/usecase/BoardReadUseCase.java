package com.example.semi_erp_sample_crud.board.usecase;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardSimpleProjection;

public interface BoardReadUseCase {
    BoardSimpleProjection read(Long id);
}
