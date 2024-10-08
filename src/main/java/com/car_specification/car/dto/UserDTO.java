package com.car_specification.car.dto;

import com.car_specification.car.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private Integer roleId;

    public UserDTO(User savedUser) {
    }
}
