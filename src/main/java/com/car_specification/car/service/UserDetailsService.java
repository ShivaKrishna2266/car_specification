package com.car_specification.car.service;

import com.car_specification.car.dto.LoginDTO;
import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.dto.UserRegistrationDTO;
import com.car_specification.car.entity.Role;
import com.car_specification.car.entity.User;
import com.car_specification.car.repository.UserRepository;
import com.car_specification.car.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public UserDTO registerUser(UserRegistrationDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role adminRole = roleService.findByRoleName(request.getRole());
        if (adminRole == null) {
            throw new RuntimeException("Admin role not found");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);
        user.setEnabled(true);
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser);
    }

    public User loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByUsername(loginDTO.getUsername());
        if (user.isPresent()) {
            return user.get();
        }
        return null;  // Invalid username or password
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

