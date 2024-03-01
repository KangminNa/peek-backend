package org.example.peek.service;

import org.example.peek.dto.SignupRequest;
import org.example.peek.entity.User;
import org.example.peek.repository.UserRepository;
import org.example.peek.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder; // PasswordEncoder 주입
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());

        // 비밀번호를 암호화하여 저장
        String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashedPassword);

        user.setNumbering(signupRequest.getNumbering());
        return userRepository.save(user);
    }
}
