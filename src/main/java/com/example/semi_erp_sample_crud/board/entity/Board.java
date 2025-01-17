package com.example.semi_erp_sample_crud.board.entity;

import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.common.jpa.support.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.Instant;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board extends BaseEntity {
    private String title;
    private String content;
    @Enumerated(EnumType.STRING)
    private BoardStatus status;
    private int likeCount;
    private int dislikeCount;
    private Instant createdAt;
    private Instant updatedAt;

}
