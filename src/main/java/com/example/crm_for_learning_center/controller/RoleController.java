package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.RoleDto;
import com.example.crm_for_learning_center.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/addRole")
    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    public HttpEntity<?> addRole(@RequestBody RoleDto roleDto){
        ApiResponse apiResponse = roleService.addRole(roleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping()
    @PreAuthorize(value = "hasAuthority('GET_ROLES')")
    public HttpEntity<?> getRoles(Pageable pageable){
        return ResponseEntity.ok(roleService.getRoles(pageable));
    }

    @PostMapping("/searchRole/{roleName}")
    @PreAuthorize(value = "hasAuthority('GET_ROLES')")
    public HttpEntity<?> searchRole(@PathVariable String roleName, Pageable pageable){
       return ResponseEntity.ok(roleService.searchRole(roleName, pageable));
    }

    @GetMapping("/getPermissions")
    @PreAuthorize(value = "hasAuthority('GET_ROLES')")
    public HttpEntity<?> getPermissions(){return ResponseEntity.ok(roleService.getPermissions());
    }

}
