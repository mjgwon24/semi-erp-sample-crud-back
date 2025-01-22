package com.example.semi_erp_sample_crud.board.service;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.*;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.mapper.BoardDtoMapper;
import com.example.semi_erp_sample_crud.board.repository.BoardRepository;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardUpdateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCreateUseCase, BoardUpdateUseCase, BoardDeleteUseCase {
    private final BoardRepository boardRepository;
    private final BoardDtoMapper mapper;

    // Create
    @Override
    public Board create(BoardCreateRequest request) {
        Instant now = Instant.now();
        Board board = mapper.toEntity(request, BoardStatus.ACTIVE, now, now);
        return boardRepository.save(board);
    }

    // Update
    @Override
    public Board update(Long id, BoardUpdateRequest request) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        Board updatedBoard = mapper.updateEntity(existingBoard, request);
        return boardRepository.save(updatedBoard);
    }

    // Delete
    @Override
    public void delete(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }
        boardRepository.deleteById(id);
    }
}
