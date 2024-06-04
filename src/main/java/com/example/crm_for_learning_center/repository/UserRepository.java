package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndEmailCode(String email, String emailCode);

    @Query("Select u.fullName ,r.name ,u.email ,u.id as values from users u inner join role r where u.role.name = 'user'")
    Page<User> getOnlyUsers(Pageable pageable);


    Optional<User> findByEmail(String email);

    @Query("select u.id, u.fullName, u.email from users u where u.email LIKE %:email% and u.role.name = 'user'")
    Page<User> searchUserByEmail(@Param("email") String email, Pageable pageable);
}
