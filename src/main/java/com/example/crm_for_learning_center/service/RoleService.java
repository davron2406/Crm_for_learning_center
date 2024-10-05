package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Role;
import com.example.crm_for_learning_center.entity.enums.Permission;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.RoleDto;
import com.example.crm_for_learning_center.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;



    public ApiResponse addRole(RoleDto roleDto){
         Optional<Role> optionalRole = roleRepository.findByName(roleDto.getName());
        if(optionalRole.isPresent())
            return new ApiResponse("Role Already exist", false,null);
        List<Permission> permissions = new ArrayList<>();
        roleDto.getPermissions().forEach(permission -> {
            permissions.add(Permission.valueOf(permission));
        });
        Role role = new Role(roleDto.getName(), permissions);
        roleRepository.save(role);

        return  new ApiResponse("Role successfully added",true,null);
    }

    public ApiResponse searchRole(String roleName, Pageable pageable) {
        return new ApiResponse("Successful", true,roleRepository.searchRole(roleName,pageable));
    }

    public ApiResponse getRoles(Pageable pageable) {
        return new ApiResponse("Successful", true,roleRepository.findAll(pageable));
    }

    public ApiResponse getPermissions() {
        Permission[] permission = Permission.values();
        List<String> permissions = new ArrayList<>();
        for(int i = 0; i < permission.length; i++) {
            permissions.add(permission[i].name());
        }

        return new ApiResponse("Successful",true,permissions);
    }
}
