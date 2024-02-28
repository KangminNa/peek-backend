// UserServiceImpl.java
package org.example.peek.service;

import org.example.peek.dto.SignupRequest;
import org.example.peek.entity.User;
import org.example.peek.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(signupRequest.getPassword());
        user.setNumbering(signupRequest.getNumbering());
        return userRepository.save(user);
    }
}
