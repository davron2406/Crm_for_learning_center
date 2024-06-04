package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.GroupDto;
import com.example.crm_for_learning_center.payload.RoleDto;
import com.example.crm_for_learning_center.payload.UserDto;
import com.example.crm_for_learning_center.repository.GroupRepository;
import com.example.crm_for_learning_center.service.GroupService;
import com.example.crm_for_learning_center.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    RoleService roleService;
    @Autowired
    private GroupRepository groupRepository;

    @PostMapping("/addGroup")
    @PreAuthorize(value = "hasAuthority('ADD_GROUP')")
    public HttpEntity<?> addGroup(@RequestBody GroupDto groupDto) throws ParseException {
       ApiResponse apiResponse = groupService.addGroup(groupDto);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/getGroups")
    @PreAuthorize(value = "hasAuthority('GET_GROUPS')")
    public HttpEntity<?> getGroups(Pageable page){
        return ResponseEntity.ok(groupService.getGroups(page));
    }

    @GetMapping("/getGroupStudents/{id}")
    @PreAuthorize(value = "hasAnyAuthority('GET_GROUPS','GET_MY_GROUPS')")
    public HttpEntity<?> getGroupStudents(@PathVariable UUID id){
        return ResponseEntity.ok(groupService.getGroupStudents(id));
    }

    @PostMapping("/addUsersToGroup/{id}")
    @PreAuthorize(value = "hasAnyAuthority('ADD_GROUP')")
    public HttpEntity<?> addUsersToGroup(@PathVariable UUID id, @RequestBody List<UUID> userIds){
        groupService.addUsersToGroup(id, userIds);
        return ResponseEntity.ok("Student successfully added to group");
    }

    @GetMapping("/getTeacherGroups/{id}")
    @PreAuthorize(value = "hasAuthority('GET_MY_GROUPS')")
    public HttpEntity<?> getTeacherGroups(@PathVariable UUID id){
        return ResponseEntity.ok(groupService.getTeacherGroups(id));
    }

    @GetMapping("/getUserGroups/{userId}")
    @PreAuthorize(value = "hasAuthority('GET_GROUPS')")
    public HttpEntity<?> getUserGroups(@PathVariable UUID userId){
       return ResponseEntity.ok(groupService.getUserGroups(userId));
    }

}
