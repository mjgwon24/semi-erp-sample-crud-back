package com.example.semi_erp_sample_crud.board.controller;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.*;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardUpdateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardDeleteUseCase;
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
    private final BoardUpdateUseCase boardUpdateUseCase;
    private final BoardDeleteUseCase boardDeleteUseCase;

    // Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BoardCreateResponse create(@RequestBody @Valid BoardCreateRequest request) {
        var board = boardCreateUseCase.create(request);
        return BoardCreateResponse.builder().board(board).build();
    }

    // Update
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardUpdateResponse update(
            @PathVariable Long id,
            @RequestBody @Valid BoardUpdateRequest request
    ) {
        var updatedBoard = boardUpdateUseCase.update(id, request);
        return BoardUpdateResponse.builder()
                .id(updatedBoard.getId())
                .title(updatedBoard.getTitle())
                .content(updatedBoard.getContent())
                .updatedAt(updatedBoard.getUpdatedAt().toString())
                .build();
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        boardDeleteUseCase.delete(id);
    }
}
