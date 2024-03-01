package org.example.peek.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 데이터베이스의 자동 증가 식별자를 사용하여 ID 생성
    private Long id;

    @Column(unique = true, nullable = false) // 고유한 값이며 null이 아니어야 함
    private String email;

    @Column(nullable = false) // null이 아니어야 함
    private String name;

    @Column(nullable = false) // null이 아니어야 함
    private String password;

    @Column(nullable = false, length = 10) // null이 아니어야 하고 최대 길이가 10인 숫자
    private String numbering;
}
