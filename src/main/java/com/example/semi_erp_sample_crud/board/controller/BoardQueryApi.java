package com.example.semi_erp_sample_crud.board.controller;

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
public class BoardQueryApi {

}
