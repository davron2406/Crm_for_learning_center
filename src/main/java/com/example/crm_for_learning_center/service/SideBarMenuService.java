package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Role;
import com.example.crm_for_learning_center.entity.SideBarMenu;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.entity.enums.Permission;
import com.example.crm_for_learning_center.repository.SideBarMenuRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SideBarMenuService {
    @Autowired
    RoleService roleService;
    @Autowired
    SideBarMenuRepository sideBarMenuRepository;
    @Autowired
    UserRepository userRepository;

    public List<SideBarMenu> getSideBarMenuBasedUser(UUID id){
        User user= userRepository.findById(id).get();
       return getSideBarMenuBasedRole(user.getRole());
    }


    public List<SideBarMenu> getSideBarMenuBasedRole(Role role){
        List<Permission> permissions = role.getPermissions();
        List<SideBarMenu> sideBarMenuList = new ArrayList<>();

        for(int i = 0; i < permissions.size(); i++){
            switch (permissions.get(i)){
                case GET_GROUPS -> sideBarMenuList.add(sideBarMenuRepository.findByName("Groups"));
                case GET_USERS -> sideBarMenuList.add(sideBarMenuRepository.findByName("Users"));
                case GET_TEACHERS -> sideBarMenuList.add(sideBarMenuRepository.findByName("Teachers"));
                case GET_ROLES -> sideBarMenuList.add(sideBarMenuRepository.findByName("Roles"));
                case GET_MY_GROUPS -> sideBarMenuList.add(sideBarMenuRepository.findByName("My Groups"));
            }
        }

        return sideBarMenuList;
    }




}
