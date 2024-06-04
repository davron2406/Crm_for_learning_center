package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Attendance;
import com.example.crm_for_learning_center.payload.AttendanceDto;
import com.example.crm_for_learning_center.payload.AttendanceInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    boolean existsAttendanceByUserIdAndGroupIdAndDate(UUID userId, UUID groupId, LocalDate date);

    Optional<Attendance> findAttendanceByUserIdAndGroupIdAndDate(UUID userId,UUID groupId, LocalDate date);

    List<Attendance> findAttendancesByGroupIdAndDate(UUID groupId, LocalDate date);

    @Query(nativeQuery = true, value = "select * from attendance a where date_part('month',a.date ) = :month and date_part('year',a.date) = :year and a.group_id = :groupId and a.user_id = :userId")
    List<Attendance> getUserGroupMonthAttendance(@Param("groupId") UUID groupId, @Param("userId") UUID userId, @Param("month") int month, @Param("year") int year);

}
