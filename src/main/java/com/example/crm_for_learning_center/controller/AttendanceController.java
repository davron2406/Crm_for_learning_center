package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.AttendanceDto;
import com.example.crm_for_learning_center.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;


    @PostMapping
    @PreAuthorize(value = "hasAuthority('GET_MY_GROUPS')")
    public HttpEntity<?> addAttendance(@RequestBody List<AttendanceDto> attendanceDtos) {
        ApiResponse apiResponse = attendanceService.addAttendance(attendanceDtos);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getGroupTodayAttendance/{groupId}")
    @PreAuthorize(value = "hasAuthority('GET_MY_GROUPS')")
    public HttpEntity<?> getGroupTodayAttendance(@PathVariable UUID groupId) {
        return ResponseEntity.ok(attendanceService.getGroupTodayAttendance(groupId));
    }

    @GetMapping("/getGroupMonthAttendance")
    @PreAuthorize(value = "hasAuthority('GET_MY_GROUPS')")
    public HttpEntity<?> getGroupMonthAttendance(@RequestParam UUID groupId,@RequestParam int month,@RequestParam int year) {
       return ResponseEntity.ok(attendanceService.getGroupMonthAttendance(groupId,month,year));
    }

    @GetMapping("/getUserMonthlyAttendance")
    @PreAuthorize(value = "hasAuthority('GET_MY_GROUPS')")
    public HttpEntity<?> getUserMonthlyAttendance(@RequestParam UUID userId, @RequestParam UUID groupId,@RequestParam int month,@RequestParam int year) {
        return ResponseEntity.ok(attendanceService.getUserMonthlyAttendanceAndHomework(userId,groupId,month,year));
    }
}
