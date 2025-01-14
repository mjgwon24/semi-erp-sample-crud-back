package com.example.semi_erp_sample_crud.board.repository;

import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardListProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<BoardListProjection> findBoardById(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Board b SET b.updatedAt = CURRENT_TIMESTAMP WHERE b.id = :id")
    void updateUpdatedAtById(Long id);
}
