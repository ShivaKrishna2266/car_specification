package com.car_specification.car.controller;

import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.entity.User;
import com.car_specification.car.service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    // Register a new user
    @PostMapping("/register")
    public UserDTO register(@RequestParam String username, @RequestParam String password) {
        return userDetailsService.registerUser(username, password);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userDetailsService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user);
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
