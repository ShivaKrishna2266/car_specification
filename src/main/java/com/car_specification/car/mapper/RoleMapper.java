package com.car_specification.car.mapper;

import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

public class RoleMapper {
    public static final ModelMapper modelMapper =new ModelMapper();
    public static RoleDTO convertToDTO(Role role){
        RoleDTO roleDTO =new RoleDTO();
        BeanUtils.copyProperties(role,roleDTO);
        return roleDTO;
    }
    public static Role convertToEntity(RoleDTO roleDTO){
        Role role=new Role();
        BeanUtils.copyProperties(roleDTO,role);
        return role;
    }
}
