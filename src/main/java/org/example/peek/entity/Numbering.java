package org.example.peek.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Numbering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numbering;

    @Column(nullable = false)
    private boolean isUsed; // 변경: 기본값을 false로 설정하지 않음
}
