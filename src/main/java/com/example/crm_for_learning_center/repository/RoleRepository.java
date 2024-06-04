package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);

    @Query("select r.name, r.id from role r where r.name like %:roleName%")
    Page<Role> searchRole(@Param("roleName")String roleName, Pageable pageable);
}
