package com.example.demo.service;

import com.example.demo.dto.role.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleDTO> getAllRole();
    RoleDTO save(RoleDTO roleDTO);
}
