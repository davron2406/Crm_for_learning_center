package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.UserDto;
import com.example.crm_for_learning_center.repository.UserRepository;
import com.example.crm_for_learning_center.service.TeacherService;
import com.example.crm_for_learning_center.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    UserService userService;

    @PutMapping("/{id}")
    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    public  HttpEntity<?> editUser(@RequestBody UserDto userDto, @PathVariable UUID id){
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('GET_USERS')")
    public HttpEntity<?> getUsers(Pageable page){
        return ResponseEntity.ok(userService.getUsers(page));
    }

    @GetMapping("/getCurrentUser")
    public  HttpEntity<?> getUserByContext(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(principal);
    }



    @GetMapping("/searchUser/{email}")
    @PreAuthorize(value = "hasAuthority('GET_USERS')")
    public HttpEntity<?> searchUser(@PathVariable String email, Pageable pageable){
        return ResponseEntity.ok(userService.searchUser(email,pageable));
    }



}
