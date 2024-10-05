package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Teacher;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.GetTeacherDto;
import com.example.crm_for_learning_center.repository.RoleRepository;
import com.example.crm_for_learning_center.repository.TeacherRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addTeacher(UUID id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            User user = byId.get();
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(user,teacher);
            teacher.setSalary(0);
            teacher.setRole(roleRepository.findByName("teacher").get());
            userRepository.delete(user);
            teacherRepository.save(teacher);
            return new ApiResponse("Teacher added successfully", true,null);
        }

        return new ApiResponse("User not found", true,null);
    }

    public ApiResponse getTeacherIdsAndNames(Pageable pageable) {
        return new ApiResponse("Successful", true,teacherRepository.getTeacherIdsAndNames(pageable));

    }

    public ApiResponse getTeachers(Pageable pageable) {
        return new ApiResponse("Successful",true,teacherRepository.getTeachers(pageable));
    }
}
