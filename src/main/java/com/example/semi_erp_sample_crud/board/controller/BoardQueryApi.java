package com.example.semi_erp_sample_crud.board.controller;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardQueryDto;
import com.example.semi_erp_sample_crud.board.usecase.BoardReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Validated
public class BoardQueryApi {

    private final BoardReadUseCase boardReadUseCase;

    @GetMapping("/read/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardQueryDto.BoardReadResponse read(@PathVariable Long id) {
        var request = BoardQueryDto.BoardReadRequest.builder().id(id).build();
        return boardReadUseCase.read(request);
    }

    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    public Page<BoardQueryDto.BoardListResponse> readAll(Pageable pageable) {
        return boardReadUseCase.readAll(pageable);
    }
}