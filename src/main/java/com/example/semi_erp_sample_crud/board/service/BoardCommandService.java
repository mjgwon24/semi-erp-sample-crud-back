package com.example.semi_erp_sample_crud.board.service;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardDeleteRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.mapper.BoardDtoMapper;
import com.example.semi_erp_sample_crud.board.repository.BoardRepository;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardSimpleProjection;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardReadUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardUpdateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BoardCommandService implements BoardCreateUseCase, BoardDeleteUseCase,BoardReadUseCase,BoardUpdateUseCase {
    private final BoardDtoMapper mapper;
    private final BoardRepository boardRepository;
    @Override
    public Board create(BoardCreateRequest request) {
        Instant now = Instant.now();

        Board board = mapper.toEntity(request, BoardStatus.ACTIVE, now, now);

        return boardRepository.save(board);
    }
    @Override
    public BoardSimpleProjection read(Long id){
        BoardSimpleProjection board = boardRepository.findBoardSimpleProjectionByIdAndStatus(id, BoardStatus.ACTIVE).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        return board;
    }
    @Override
    public void delete(BoardDeleteRequest request){
        Board board = boardRepository.findBoardById(request.id()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        mapper.softDeleteEntity(board,request);
        boardRepository.save(board);
    }
    @Override
    public void hardDelete(BoardDeleteRequest request){
        Board board = boardRepository.findBoardById(request.id()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        boardRepository.delete(board);
    }
    @Override
    public Board update(BoardUpdateRequest request){
        Instant now = Instant.now();
        Board board = boardRepository.findBoardDetailByIdAndStatus(request.id(), BoardStatus.ACTIVE).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        mapper.updateEntity(board, request, now);
        return boardRepository.save(board);
    }
}
