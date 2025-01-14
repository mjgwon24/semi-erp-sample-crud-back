package com.example.semi_erp_sample_crud.board.controller;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateResponse;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Validated
public class BoardCommandApi {
    private final BoardCreateUseCase boardCreateUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardCreateResponse create(
            @RequestBody @Valid BoardCreateRequest request
    ) {
        var board = boardCreateUseCase.create(request);

        return BoardCreateResponse.builder()
                .board(board)
                .build();
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public BoardCreateResponse update(
            @RequestBody @Valid BoardUpdateRequest request
    ) {
        var board = boardCreateUseCase.update(request);

        return BoardCreateResponse.builder()
                .board(board)
                .build();
    }
}
