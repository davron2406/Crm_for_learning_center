package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Group;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.GetGroupDto;
import com.example.crm_for_learning_center.payload.GroupDto;
import com.example.crm_for_learning_center.payload.GroupUsersWithPayment;
import com.example.crm_for_learning_center.repository.GroupRepository;
import com.example.crm_for_learning_center.repository.TeacherRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;

    public ApiResponse addGroup(GroupDto groupDto) throws ParseException {
        List<User> students = new ArrayList<>();
        groupDto.getStudentIds().forEach(id -> {
            students.add(userRepository.findById(id).get());
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(groupDto.getStartDate());

        Group group = new Group();
        group.setTeacher(Arrays.asList(teacherRepository.findById(groupDto.getTeacherId()).get()));
        group.setName(groupDto.getName());
        group.setStartDate(parsedDate);
        group.setUsers(students);
        group.setStartTime(LocalTime.parse(groupDto.getStartTime()));

        groupRepository.save(group);

        return new ApiResponse("Successful",true, null);
    }

    public ApiResponse getGroups(Pageable page) {
        return new ApiResponse("Successful",true,groupRepository.findAll(page));
    }

    public ApiResponse getGroupStudents(UUID groupId) {
        System.out.println(new Date().getYear());
        return new ApiResponse("Successful", true,groupRepository.findGroupUsersWithPayment(groupId, Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.YEAR)));
    }

    public ApiResponse addUsersToGroup(UUID id, List<UUID> userIds) {
        List<User> students = new ArrayList<>();
        userIds.forEach(studentId -> {
           students.add(userRepository.findById(studentId).get());
        });
        Group group = groupRepository.findById(id).get();
        List<User> groupUsers = group.getUsers();
        groupUsers.addAll(students);
        group.setUsers(groupUsers);
        groupRepository.save(group);
        return new ApiResponse("Successful",true, null);
    }



    public ApiResponse getTeacherGroups(UUID id) {
       return new ApiResponse("Successful", true,groupRepository.findByTeacherId(id));
    }

    public ApiResponse getUserGroups(UUID userId) {
        return new ApiResponse("Successfull", true,groupRepository.findByUserId(userId));
    }

    public ApiResponse removeUser(UUID userId, UUID groupId) {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            return new ApiResponse("UserNotFound",false,null);
        }
        Optional<Group> groupById = groupRepository.findById(groupId);
        if (groupById.isEmpty()) {
            return new ApiResponse("GroupNotFound",false,null);
        }
        groupById.get().getUsers().remove(userById.get());

        groupRepository.save(groupById.get());
        return new ApiResponse("UserRemoved",true,null);
    }
}
