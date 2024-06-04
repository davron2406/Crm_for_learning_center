package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.UserDto;
import com.example.crm_for_learning_center.repository.RoleRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;
    public ApiResponse editUser(UUID id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new ApiResponse("User Not found",false);

        User user = optionalUser.get();
        User editedUser = userDtotoUser(userDto, user);
        userRepository.save(editedUser);
        return new ApiResponse("User successfully edited",true);
    }

    private User userDtotoUser(UserDto userDto, User user){
        if(userDto.getFullName() != null)
            user.setFullName(userDto.getFullName());
        if(userDto.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if(userDto.getRoleId() != null){
            user.setRole(roleRepository.findById(userDto.getRoleId()).get());
        }
        if(userDto.getEmail() != null){
            user.setEmail(userDto.getEmail());
        }

        return user;
    }

    public Page<User> getUsers(Pageable page){
        System.out.println(userRepository.getOnlyUsers(page));
        return  userRepository.getOnlyUsers(page);

    }


    public Page<User> searchUser(String email,Pageable pageable) {
        return userRepository.searchUserByEmail(email,pageable);
    }
}
