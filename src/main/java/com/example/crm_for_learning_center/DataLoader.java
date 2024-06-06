package com.example.crm_for_learning_center;


import com.example.crm_for_learning_center.entity.Role;
import com.example.crm_for_learning_center.entity.SideBarMenu;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.entity.enums.Permission;
import com.example.crm_for_learning_center.repository.RoleRepository;
import com.example.crm_for_learning_center.repository.SideBarMenuRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import com.example.crm_for_learning_center.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SideBarMenuRepository sideBarMenuRepository;



    @Override
    public void run(String... args) throws Exception {

//        SideBarMenu sideBarMenu = new SideBarMenu("Groups","groups","/groups");
//        SideBarMenu sideBarMenu1 = new SideBarMenu("Teacher","cast_for_education","/teachers");
//        SideBarMenu sideBarMenu2 = new SideBarMenu("Users","person","/users");
//        SideBarMenu sideBarMenu3 = new SideBarMenu("Roles","workspace_premium","/roles");
//        SideBarMenu sideBarMenu4 = new SideBarMenu("My Groups","groups","/myGroups");
//        sideBarMenuRepository.save(sideBarMenu);
//        sideBarMenuRepository.save(sideBarMenu1);
//        sideBarMenuRepository.save(sideBarMenu2);
//        sideBarMenuRepository.save(sideBarMenu3);
//        sideBarMenuRepository.save(sideB arMenu4);
//
//            Permission[] permissions = Permission.values();
//            Role admin = roleRepository.save(new Role(AppConstants.ADMIN, Arrays.asList(permissions)));
//            roleRepository.save(new Role(AppConstants.USER, List.of()));
//            userRepository.save(new User("Admin", passwordEncoder.encode( "admin123"),"admin123@gmail.com",null, 1,admin, true, true, true, true));


    }
}
