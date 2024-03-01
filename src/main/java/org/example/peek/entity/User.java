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

    @OneToOne(fetch = FetchType.LAZY) // User가 Numbering에 대해 OneToOne 관계를 가짐
    @JoinColumn(name = "numbering_id") // User 테이블의 numbering_id 칼럼이 Numbering 테이블의 id 칼럼을 가리킴
    private Numbering numbering;


}
