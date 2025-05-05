package com.car_specification.car.mapper;

import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

public class UserMapper {
    public static final ModelMapper modelMapper=new ModelMapper();
    public static UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setMobile(user.getMobile());

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            String roleNames = user.getRoles().stream()
                    .map(role -> role.getRoleName())
                    .collect(Collectors.joining(", "));
            dto.setRole(roleNames);
        } else {
            dto.setRole(null);
        }
        dto.setCreatedBy(user.getCreatedBy());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedBy(user.getUpdatedBy());
        dto.setUpdatedAt(user.getUpdatedAt());

        if (user.getEvents() != null) {
            dto.setEventId(user.getEvents().getEventId());
        } else {
            dto.setEventId(null);
        }

        return dto;
    }
    public static User convertToEntity(UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
}
