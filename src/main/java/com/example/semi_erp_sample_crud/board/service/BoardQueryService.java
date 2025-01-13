package com.example.semi_erp_sample_crud.board.service;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.mapper.BoardDtoMapper;
import com.example.semi_erp_sample_crud.board.repository.BoardRepository;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BoardQueryService {}
