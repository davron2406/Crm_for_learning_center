package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Group;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.GetGroupDto;
import com.example.crm_for_learning_center.payload.GroupDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query("select g.users from groups g where g.id=?1")
    List<User> findGroupUsers(UUID id);

    List<Group> findByTeacherId(UUID id);

    @Query(nativeQuery = true, value = "select g.name,g.id,g.start_date,g.start_time from groups g inner join groups_users gu on gu.groups_id=g.id where gu.users_id=:userId")
    List<GetGroupDto> findByUserId(@Param("userId") UUID id);
}
