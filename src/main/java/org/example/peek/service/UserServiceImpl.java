package org.example.peek.service;

import org.example.peek.dto.LoginRequest;
import org.example.peek.dto.SignupRequest;
import org.example.peek.entity.Numbering;
import org.example.peek.entity.User;
import org.example.peek.repository.NumberingRepository;
import org.example.peek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입
    private final NumberingRepository numberingRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, NumberingRepository numberingRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // PasswordEncoder 주입
        this.numberingRepository = numberingRepository;
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        // Numbering 엔티티를 찾아서 설정
        Numbering numbering = numberingRepository.findByNumbering(signupRequest.getNumbering())
                .orElseThrow(() -> new RuntimeException("Numbering not found"));

        if (numbering.isUsed()) {
            throw new RuntimeException("Numbering is already used");
        }

        // 사용된 번호로 표시
        numbering.setUsed(true);
        numberingRepository.save(numbering);

        // 사용자 생성 및 저장
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setNumbering(numbering);

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        return userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found")); // 예외 처리를 원하는 방식으로 수정하세요
    }

}
