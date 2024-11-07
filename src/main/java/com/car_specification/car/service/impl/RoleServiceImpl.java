package com.car_specification.car.service.impl;

import com.car_specification.car.dto.RoleDTO;
import com.car_specification.car.entity.Role;
import com.car_specification.car.mapper.RoleMapper;
import com.car_specification.car.repository.RoleRepository;
import com.car_specification.car.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;
    @Override
    public Role findByRoleName(String name) {
        return repository.findByRoleName(name);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
           List<Role> roles = repository.findAll();
            return roles.stream()
                .map(r -> RoleMapper.convertToDTO(r))
                .collect(Collectors.toList());
    }
}
