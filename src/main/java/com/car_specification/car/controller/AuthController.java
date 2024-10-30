package com.car_specification.car.controller;

import com.car_specification.car.config.CustomUserDetailsService;
import com.car_specification.car.config.JwtUtil;
import com.car_specification.car.dto.LoginDTO;
import com.car_specification.car.dto.UserRegistrationDTO;
import com.car_specification.car.entity.Role;
import com.car_specification.car.entity.User;
import com.car_specification.car.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO request) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        if(userDetails == null){
            String errorMessage = "Active user is not found";
            return ResponseEntity.badRequest().body(errorMessage);
        }
        final Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String jwt = jwtUtil.generateToken(authentication);
        Map<String, String> response = new HashMap<>();
        User user = userDetailsService.findByUsername(request.getUsername());
        Role role = user.getRoles().stream().findFirst().get();
        response.put("token", jwt);
        response.put("username", user.getUsername());
        response.put("role",role.getRoleName());
         return ResponseEntity.ok(response);
    }


}
