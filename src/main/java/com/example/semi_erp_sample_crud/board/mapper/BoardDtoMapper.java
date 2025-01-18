package com.example.semi_erp_sample_crud.board.mapper;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BoardDtoMapper {
    @Mapping(target = "likeCount", ignore = true)
    @Mapping(target = "dislikeCount", ignore = true)
    Board toEntity(BoardCreateRequest dto, BoardStatus status, Instant createdAt, Instant updatedAt);

    @Mapping(target = "createdAt", expression = "java(board.getCreatedAt().toString())")
    @Mapping(target = "updatedAt", expression = "java(board.getUpdatedAt().toString())")
    BoardQueryDto.BoardReadResponse toReadResponse(Board board); // 반환 타입 수정// Entity to Dto 서버 to 클라이언트

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    Board toEntity(BoardQueryDto.BoardReadRequest dto); // DTO to Entity 클라이언트 to 서버 필드 다 dto에 있어서 dto 만 쓰면 됨
}
