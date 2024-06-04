package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Teacher;
import com.example.crm_for_learning_center.payload.GetTeacherDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {

    @Query("select t.id, t.fullName from teachers t")
    Page<Teacher> getTeacherIdsAndNames(Pageable pageable);

    @Query(value = "select t.full_name as fullName, t.id as id,t.salary as salary, count(gt.groups_id) as groupCount from users t inner join public.groups_teacher gt on t.id = gt.teacher_id where t.dtype = 'teachers' group by  t.id", nativeQuery = true)
    Page<GetTeacherDto> getTeachers(Pageable pageable);

}
