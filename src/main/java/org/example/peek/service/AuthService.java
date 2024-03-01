package org.example.peek.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.peek.dto.LoginRequest;
import org.example.peek.entity.User;
import org.example.peek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // JWT 시크릿 키
    @Value("${jwt.secret}")
    private String jwtSecret;

    // JWT 만료 시간 (여기서는 1시간)
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String authenticateUser(LoginRequest loginRequest) {
        // 사용자 검증
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // JWT 토큰 생성
        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        // 현재 시간
        Date now = new Date();
        // 토큰 만료 시간
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // JWT 토큰 생성
        return Jwts.builder()
                .setSubject(Long.toString(user.getId())) // 토큰 제목에 사용자 ID 저장
                .setIssuedAt(now) // 토큰 발급 시간 설정
                .setExpiration(expiryDate) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // HS512 알고리즘 및 시크릿 키로 서명
                .compact();
    }
}
