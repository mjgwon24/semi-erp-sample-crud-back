package com.example.semi_erp_sample_crud.board.repository;

import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardListProjection;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardDetailProjection;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardSimpleProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.beans.Transient;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<BoardListProjection> findBoardProjectionById(Long id);
    Optional<Board> findBoardById(Long id);
    Optional<BoardDetailProjection> findBoardDetailById(Long id);
    Optional<Board> findBoardDetailByIdAndStatus(Long id, BoardStatus status);
    Optional<BoardSimpleProjection> findBoardSimpleProjectionByIdAndStatus(Long id, BoardStatus status);
}
