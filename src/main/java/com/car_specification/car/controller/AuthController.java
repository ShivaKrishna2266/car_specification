package com.car_specification.car.controller;

import com.car_specification.car.dto.LoginDTO;
import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.entity.User;
import com.car_specification.car.service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
        userDetailsService.registerUser(username,password);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        User user = userDetailsService.loginUser(loginDTO);
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
