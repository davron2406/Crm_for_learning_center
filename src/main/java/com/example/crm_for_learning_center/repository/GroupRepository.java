package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Group;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.GetGroupDto;
import com.example.crm_for_learning_center.payload.GroupDto;
import com.example.crm_for_learning_center.payload.GroupUsersWithPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

    @Query(nativeQuery =true, value="select u.full_name as fullName, u.email as email, u.id as id,p.payment_amounts as paymentAmounts from groups_users g\n" +
            "    left join public.users u on g.users_id = u.id\n" +
            "    left join public.payment p on(p.user_id = u.id and p.month = :month and p.year = :year)\n" +
            "    where g.groups_id=:groupId")
    List<GroupUsersWithPayment> findGroupUsersWithPayment(@Param("groupId") UUID groupId, @Param("month") int month, @Param("year") int year);

    @Query("select g.users from groups g where g.id=:groupId")
    List<User> findGroupUsers(@Param("groupId") UUID groupId);

    List<Group> findByTeacherId(UUID id);

    @Query(nativeQuery = true, value = "select g.name,g.id,g.start_date,g.start_time from groups g inner join groups_users gu on gu.groups_id=g.id where gu.users_id=:userId")
    List<GetGroupDto> findByUserId(@Param("userId") UUID id);
}
