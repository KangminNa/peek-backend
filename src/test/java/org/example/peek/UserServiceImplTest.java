package org.example.peek;

import org.example.peek.dto.SignupRequest;
import org.example.peek.entity.User;
import org.example.peek.repository.UserRepository;
import org.example.peek.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private final UserServiceImpl userService;

    public UserServiceImplTest() {
        this.userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void testSignup() {
        // Given
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("test@example.com");
        signupRequest.setName("Test User");
        signupRequest.setPassword("password");
        signupRequest.setNumbering("123456");

        User expectedUser = new User();
        expectedUser.setEmail("test@example.com");
        expectedUser.setName("Test User");
        expectedUser.setPassword("encodedPassword");
        expectedUser.setNumbering("123456");

        Mockito.when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(expectedUser);

        // When
        User actualUser = userService.signup(signupRequest);

        // Then
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getNumbering(), actualUser.getNumbering());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }
}
