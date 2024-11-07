package com.car_specification.car.service;

import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.exception.ApplicationBusinessException;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO getUserById(Integer userId);

    UserDTO createUser(UserDTO userDTO) throws ApplicationBusinessException;

    UserDTO updateUser(Integer userId, UserDTO userDTO);

    void deleteUserById(Integer userId);

}
