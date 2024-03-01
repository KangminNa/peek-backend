// UserService.java
package org.example.peek.service;

import org.example.peek.dto.LoginRequest;
import org.example.peek.dto.SignupRequest;
import org.example.peek.entity.User;

public interface UserService {
    User signup(SignupRequest signupRequest);
    User login(LoginRequest loginRequest);
}
