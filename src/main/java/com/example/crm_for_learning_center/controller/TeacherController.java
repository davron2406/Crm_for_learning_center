package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/addTeacher")
    @PreAuthorize(value = "hasAuthority('ADD_TEACHER')")
    public HttpEntity<?> addTeacher(@RequestParam UUID id) {
        ApiResponse apiResponse = teacherService.addTeacher(id);
        return  ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/getTeacherIdsAndNames")
    @PreAuthorize(value = "hasAuthority('GET_TEACHERS')")
    public HttpEntity<?> getTeacherIdsAndNames(Pageable pageable){
       return ResponseEntity.ok(teacherService.getTeacherIdsAndNames(pageable));
    }

    @GetMapping("/getTeachers")
    @PreAuthorize(value = "hasAuthority('GET_TEACHERS')")
    public HttpEntity<?> getTeachers(Pageable pageable){
      return ResponseEntity.ok(teacherService.getTeachers(pageable));
    }
}
