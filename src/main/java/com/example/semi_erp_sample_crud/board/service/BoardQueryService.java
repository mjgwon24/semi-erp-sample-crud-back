package com.example.semi_erp_sample_crud.board.service;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardListResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto.BoardReadResponse;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.mapper.BoardDtoMapper;
import com.example.semi_erp_sample_crud.board.repository.BoardRepository;
import com.example.semi_erp_sample_crud.board.usecase.BoardReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardReadUseCase {
    private final BoardRepository boardRepository;
    private final BoardDtoMapper mapper;

    @Override
    public BoardReadResponse read(BoardReadRequest request) {
        Board board = boardRepository.findById(request.id())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return mapper.toReadResponse(board);
    }

    @Override
    public Page<BoardListResponse> readAll(Pageable pageable) {
        return boardRepository.findAllByIsDeletedFalse(pageable)
                .map(mapper::toListResponse); // 엔티티를 DTO로 변환
    }
}