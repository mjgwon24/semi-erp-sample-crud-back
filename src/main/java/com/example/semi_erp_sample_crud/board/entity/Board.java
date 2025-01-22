package com.example.semi_erp_sample_crud.board.entity;

import com.example.semi_erp_sample_crud.board.entity.type.BoardStatus;
import com.example.semi_erp_sample_crud.common.jpa.support.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE board SET is_deleted = true WHERE id = ?") // Soft Delete SQL
@Where(clause = "is_deleted = false") // 조회 시 삭제되지 않은 데이터만 포함
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    private int likeCount;
    private int dislikeCount;

    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private boolean isDeleted = false; // Soft Delete 상태
}