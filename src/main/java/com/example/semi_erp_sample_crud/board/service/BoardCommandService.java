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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.time.Instant;
import java.util.List;

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
    public List<BoardSimpleProjection> readByDate(Instant startDate, Instant endDate){
        List<BoardSimpleProjection> boards = boardRepository.findByCreatedAtBetweenAndStatus(startDate,endDate, BoardStatus.ACTIVE).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        return boards;
    }
    @Override
    public List<BoardSimpleProjection> readById(Long startId, Long endId){
        List<BoardSimpleProjection> boards = boardRepository.findByIdBetweenAndStatus(startId,endId, BoardStatus.ACTIVE).orElseThrow(() -> {
            throw new IllegalArgumentException("해당하는 게시글이 없습니다");
        });
        return boards;
    }
    @Override
    public List<BoardSimpleProjection> readAsPage(Long page, Long pageSize, boolean ascending) {
        // 정렬 설정
        Sort sort = ascending ? Sort.by("createdAt").ascending() : Sort.by("createdAt").descending();

        // Pageable 객체 생성
        Pageable pageable = PageRequest.of(page.intValue(), pageSize.intValue(), sort);

        // 데이터 조회
        return boardRepository.findAllBy(pageable);
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
