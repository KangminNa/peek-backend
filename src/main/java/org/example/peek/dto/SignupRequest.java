// SignupRequest.java
package org.example.peek.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String email;
    private String name;
    private String password;
    private String numbering;
}
