package com.example.semi_erp_sample_crud.board.usecase;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardSimpleProjection;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public interface BoardReadUseCase {
    BoardSimpleProjection read(Long id);
    List<BoardSimpleProjection> readByDate(Instant startDate, Instant endDate);
    List<BoardSimpleProjection> readById(Long startId, Long endId);
    List<BoardSimpleProjection> readAsPage(Long page, Long pageSize, boolean ascending);
}
