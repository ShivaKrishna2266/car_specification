package com.car_specification.car.service.impl;

import com.car_specification.car.entity.Role;
import com.car_specification.car.repository.RoleRepository;
import com.car_specification.car.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;
    @Override
    public Role findByRoleName(String name) {
        return repository.findByRoleName(name);
    }
}
