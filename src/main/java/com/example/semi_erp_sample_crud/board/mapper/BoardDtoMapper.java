package com.example.semi_erp_sample_crud.board.mapper;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardDeleteRequest;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface BoardDtoMapper {
    Board toEntity(BoardCreateRequest dto, BoardStatus status, Instant createdAt, Instant updatedAt);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Board updateEntity(@MappingTarget Board board,
                       BoardUpdateRequest updateRequest,
                       Instant updatedAt);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", ignore = true)
    @Mapping(target = "content", ignore = true)
    @Mapping(target = "status", expression = "java(BoardStatus.REMOVED)")
    Board softDeleteEntity(@MappingTarget Board board, BoardDeleteRequest deleteRequest);
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "title", ignore = true)
//    @Mapping(target = "content", ignore = true)
//    @Mapping(target = "status", expression = "java(BoardStatus.REMOVED)") // 명시적으로 값 설정
//    Board softDeleteEntity(@MappingTarget Board board, BoardDeleteRequest deleteRequest);
//    default Board softDeleteEntity(@MappingTarget Board board, BoardDeleteRequest deleteRequest) {
//        if (deleteRequest == null) {
//            return board;
//        }
//        board.setStatus(BoardStatus.REMOVED);
//        return board;
//    }
}
