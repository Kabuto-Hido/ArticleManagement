package com.example.demo.mapper;

import com.example.demo.dto.role.RoleDTO;
import com.example.demo.entity.Role;

public class RoleMapper {
    public static RoleDTO toRoleDto (Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setCreatedDate(role.getCreatedDate());
        roleDTO.setModifiedDate(role.getModifiedDate());
        return roleDTO;
    }

    public static Role toEntity (RoleDTO roleDTO){
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
