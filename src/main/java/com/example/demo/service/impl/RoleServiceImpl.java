package com.example.demo.service.impl;

import com.example.demo.dto.role.RoleDTO;
import com.example.demo.entity.Role;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleDTO> getAllRole() {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for(Role role : roles){
            roleDTOS.add(RoleMapper.toRoleDto(role));
        }
        return roleDTOS;
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role;
        if(roleDTO.getId() != 0L){
            role = roleRepository.findById(roleDTO.getId())
                    .orElseThrow(() -> new NotFoundException("Role not exist"));

            role.setName(roleDTO.getName());
        }
        else {
            role = RoleMapper.toEntity(roleDTO);
        }
        role = roleRepository.save(role);
        return RoleMapper.toRoleDto(role);
    }
}
