package com.example.crm_for_learning_center.repository;


import com.example.crm_for_learning_center.entity.SideBarMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SideBarMenuRepository extends JpaRepository<SideBarMenu, UUID> {

    SideBarMenu findByName(String name);
}
