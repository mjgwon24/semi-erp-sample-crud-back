package com.example.semi_erp_sample_crud.board.mapper;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface BoardDtoMapper {
    Board toEntity(BoardCreateRequest dto,
                   BoardStatus status,
                   Instant createdAt,
                   Instant updatedAt);

    @Mapping(target = "id", ignore = true) // id는 변경하지 않음
    @Mapping(target = "createdAt", ignore = true) // createdAt은 변경하지 않음
    Board updateEntity(@MappingTarget Board exisitingBoard,
                       BoardUpdateRequest dto,
                       BoardStatus status,
                       Instant updatedAt);
}
