package com.car_specification.car.service;

import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByRoleName(String name);
    List<RoleDTO> getAllRoles();
}
