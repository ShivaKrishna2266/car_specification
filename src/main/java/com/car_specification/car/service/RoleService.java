package com.car_specification.car.service;

import com.car_specification.car.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
