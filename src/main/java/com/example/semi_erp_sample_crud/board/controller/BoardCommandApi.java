package com.example.semi_erp_sample_crud.board.controller;

import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardCreateResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardDeleteRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardDeleteResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardReadResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateRequest;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.BoardUpdateResponse;
import com.example.semi_erp_sample_crud.board.controller.dto.BoardCommandDto.ReadBoardListResponse;
import com.example.semi_erp_sample_crud.board.entity.Board;
import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.board.repository.projection.BoardProjection.BoardSimpleProjection;
import com.example.semi_erp_sample_crud.board.usecase.BoardCreateUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardDeleteUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardReadUseCase;
import com.example.semi_erp_sample_crud.board.usecase.BoardUpdateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Validated
public class BoardCommandApi {
    private final BoardCreateUseCase boardCreateUseCase;
    private final BoardDeleteUseCase boardDeleteUseCase;
    private final BoardUpdateUseCase boardUpdateUseCase;
    private final BoardReadUseCase boardReadUseCase;

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

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BoardReadResponse read(
            @RequestParam Long id
    ){

        BoardSimpleProjection board = boardReadUseCase.read(id);
        BoardReadResponse response = BoardReadResponse.builder().board(board).build();
        return response;
    }
    @GetMapping("/ListByDate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReadBoardListResponse readByDate(
            @RequestParam Instant minDate,
            @RequestParam Instant maxDate
    ){
        List<BoardSimpleProjection> boardList = boardReadUseCase.readByDate(minDate, maxDate);
        ReadBoardListResponse response = ReadBoardListResponse.builder().boardList(boardList).build();
        return response;
    }
    @GetMapping("/ListById")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReadBoardListResponse readByDate(
            @RequestParam Long minId,
            @RequestParam Long maxId
    ){
        List<BoardSimpleProjection> boardList = boardReadUseCase.readById(minId,maxId);
        ReadBoardListResponse response = ReadBoardListResponse.builder().boardList(boardList).build();
        return response;
    }

    @GetMapping("/page")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ReadBoardListResponse readAsPage(
            @RequestParam Long page,
            @RequestParam Long pageSize,
            @RequestParam boolean ascending
    ){
        List<BoardSimpleProjection> boardList = boardReadUseCase.readAsPage(page, pageSize,ascending);
        ReadBoardListResponse response = ReadBoardListResponse.builder().boardList(boardList).build();
        return response;
    }


    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BoardUpdateResponse update(
            @RequestBody @Valid BoardUpdateRequest UpdateRequest
    ){
        Board board = boardUpdateUseCase.update(UpdateRequest);
        return BoardUpdateResponse.builder().board(board).build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BoardDeleteResponse delete(
            @RequestBody @Valid BoardDeleteRequest DeleteRequest
    ){
        boardDeleteUseCase.delete(DeleteRequest);
        return BoardDeleteResponse.builder().status(BoardStatus.REMOVED).build();
    }

    @DeleteMapping("/hard")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BoardDeleteResponse harddelete(
            @RequestBody @Valid BoardDeleteRequest DeleteRequest
    ){
        boardDeleteUseCase.hardDelete(DeleteRequest);
        return BoardDeleteResponse.builder().status(BoardStatus.REMOVED).build();
    }
}
