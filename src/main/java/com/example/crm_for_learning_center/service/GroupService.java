package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Group;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.GetGroupDto;
import com.example.crm_for_learning_center.payload.GroupDto;
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

        return new ApiResponse("Muvaffaqiyatli",true);
    }

    public Page<Group> getGroups(Pageable page) {
        return groupRepository.findAll(page);
    }

    public List<User> getGroupStudents(UUID id) {
        return groupRepository.findGroupUsers(id);
    }

    public boolean addUsersToGroup(UUID id, List<UUID> userIds) {
        List<User> students = new ArrayList<>();
        userIds.forEach(studentId -> {
           students.add(userRepository.findById(studentId).get());
        });
        Group group = groupRepository.findById(id).get();
        List<User> groupUsers = group.getUsers();
        groupUsers.addAll(students);
        group.setUsers(groupUsers);
        groupRepository.save(group);
        return true;
    }

    public List<Group> getTeacherGroups(UUID id) {
       return groupRepository.findByTeacherId(id);
    }

    public List<GetGroupDto> getUserGroups(UUID userId) {
        return groupRepository.findByUserId(userId);
    }
}
