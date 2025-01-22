package com.example.semi_erp_sample_crud.board.repository;

import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<BoardListProjection> findBoardById(Long id);
    Optional<BoardProjection.BoardReadProjection> findReadProjectionById(Long id);
    Page<Board> findAllByIsDeletedFalse(Pageable pageable);
}
