package com.example.demo.controller;

import com.example.demo.dto.role.RoleDTO;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @PostMapping("/createNew")
    public ResponseEntity<?> createNewRole(@RequestBody RoleDTO model){
        return ResponseEntity.ok(roleService.save(model));
    }
//
//    @PutMapping("/role/{id}")
//    public ResponseEntity<?> updateRole(@PathVariable long id, @RequestBody RoleDTO model){
//        model.setId(id);
//        return ResponseEntity.ok(roleService.save(model));
//    }
}
