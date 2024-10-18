package com.usedcardealer.control;

import com.usedcardealer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create a DTO for the login request
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public ResponseEntity<?> handleLoginRequest(@RequestBody LoginRequest loginRequest) {
        try {
            User user = User.authenticateUser(jdbcTemplate, loginRequest.username, loginRequest.password);
            // Create a session or return user info as needed
            return ResponseEntity.ok(user); 
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); 
        }
    }
}