package com.usedcardealer.control;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity<?> handleLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); 
        if (session != null) {
            session.invalidate(); 
        }
        return ResponseEntity.ok("Logged out successfully"); 
    }
}