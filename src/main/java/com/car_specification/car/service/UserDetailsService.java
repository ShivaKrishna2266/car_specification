package com.car_specification.car.service;

import com.car_specification.car.dto.LoginDTO;
import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.dto.UserRegistrationDTO;
import com.car_specification.car.entity.Role;
import com.car_specification.car.entity.User;
import com.car_specification.car.entity.UserProfile;
import com.car_specification.car.repository.UserProfileRepository;
import com.car_specification.car.repository.UserRepository;
import com.car_specification.car.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserDTO registerUser(UserDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role adminRole = roleService.findByRoleName(request.getRole());
        if (adminRole == null) {
            throw new RuntimeException("Admin role not found");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);
        user.setEnabled(true);
        userRepository.save(user);

        UserProfile userProfile =new UserProfile();
        userProfile.setUserName(request.getUsername());
        userProfile.setEmail(request.getEmail());
        userProfile.setMobileNumber(request.getMobile());
        userProfile.setPassword(request.getPassword());
        userProfile.setCreatedBy("System");
        userProfile.setUpdatedBy("System");
        userProfile.setCreatedAt(LocalDateTime.now());
        userProfile.setUpdatedAt(LocalDateTime.now());

        userProfileRepository.save(userProfile);
        return request;

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

