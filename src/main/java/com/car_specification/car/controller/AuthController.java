package com.car_specification.car.controller;

import com.car_specification.car.config.CustomUserDetailsService;
import com.car_specification.car.config.JwtUtil;
import com.car_specification.car.dto.LoginDTO;
import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.dto.UserRegistrationDTO;
import com.car_specification.car.entity.User;
import com.car_specification.car.service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO request) {
        userDetailsService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }


    @PostMapping("/authenticate")
    public Map<String, String> authenticate(@RequestBody LoginDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());
        String jwt = jwtUtil.generateToken(new HashMap<>(), user.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }
}
