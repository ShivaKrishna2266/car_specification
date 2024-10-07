package com.car_specification.car.mapper;

import com.car_specification.car.dto.UserDTO;
import com.car_specification.car.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static final ModelMapper modelMapper=new ModelMapper();
    public static UserDTO convertToDTO(User user){
        UserDTO userDTO= new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }
    public static User convertToEntity(UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        return user;
    }
}
