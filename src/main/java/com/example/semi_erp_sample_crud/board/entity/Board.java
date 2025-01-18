package com.example.semi_erp_sample_crud.board.entity;

import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.common.jpa.support.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA ID 설정
    private Long id;
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private BoardStatus status;
    private int likeCount;
    private int dislikeCount;
    private Instant createdAt;
    private Instant updatedAt;
}
