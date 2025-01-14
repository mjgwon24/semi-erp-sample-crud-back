package com.example.semi_erp_sample_crud.board.service;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.mapper.BoardDtoMapper;
import com.example.semi_erp_sample_crud.board.repository.BoardRepository;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCreateUseCase {
    private final BoardDtoMapper mapper;
    private final BoardRepository boardRepository;
    @Override
    public Board create(BoardCreateRequest request) {
        Instant now = Instant.now();

        Board board = mapper.toEntity(request, BoardStatus.ACTIVE, now, now);

        return boardRepository.save(board);
    }

    @Override
    public Board update(BoardUpdateRequest request) {
        // 기존 엔티티 조회
        Board existingBoard = boardRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 현재 시간 설정
        Instant now = Instant.now();
        mapper.updateEntity(existingBoard, request, BoardStatus.ACTIVE, now);

        // 변경사항 저장
        return boardRepository.save(existingBoard);
    }
}
