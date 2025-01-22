package com.example.semi_erp_sample_crud.board.mapper;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BoardDtoMapper {
    // Create 작업: DTO -> Entity
    @Mapping(target = "likeCount", ignore = true)
    @Mapping(target = "dislikeCount", ignore = true)
    Board toEntity(BoardCreateRequest dto, BoardStatus status, Instant createdAt, Instant updatedAt);

    // Read 작업: Entity -> DTO
    @Mapping(target = "createdAt", expression = "java(board.getCreatedAt().toString())")
    @Mapping(target = "updatedAt", expression = "java(board.getUpdatedAt().toString())")
    BoardQueryDto.BoardReadResponse toReadResponse(Board board);

    // Update 작업: 빌더를 이용한 수정
    default Board updateEntity(Board entity, BoardCommandDto.BoardUpdateRequest dto) {
        return entity.toBuilder()
                .title(dto.title())
                .content(dto.content())
                .updatedAt(Instant.now())
                .build();
    }
    // Read 작업 (조건 검색): DTO -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "title")
    @Mapping(target = "content", source = "content")
    Board toEntity(BoardQueryDto.BoardReadRequest dto);
    BoardQueryDto.BoardListResponse toListResponse(Board board);
}
