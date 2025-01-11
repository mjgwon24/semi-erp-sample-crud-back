package com.example.semi_erp_sample_crud.board.mapper;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import org.mapstruct.Mapper;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface BoardDtoMapper {
    Board toEntity(BoardCreateRequest dto, BoardStatus status, Instant createdAt, Instant updatedAt);
}
