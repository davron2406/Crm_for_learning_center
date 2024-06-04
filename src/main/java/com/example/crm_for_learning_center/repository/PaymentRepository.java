package com.example.crm_for_learning_center.repository;

import com.example.crm_for_learning_center.entity.Payment;
import com.example.crm_for_learning_center.payload.GetPaymentDto;
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
public interface PaymentRepository extends JpaRepository<Payment, UUID> {


    Optional<Payment> findByUserIdAndAndGroupIdAndMonthAndYear(UUID userId, UUID groupId,int month, int year);

    @Query(nativeQuery = true,value = "select p.id as id,u.full_name as fullName, g.name as groupName, p.month as month, p.year as year, p.payment_amounts as paymentAmounts from payment p inner join users u on u.id = p.user_id inner join groups g on g.id = p.group_id")
    Page<GetPaymentDto> findAllPaymentsWithUserNamesAndGroupName(Pageable pageable);

    @Query(nativeQuery = true,value = "select p.id as id,u.full_name as fullName, g.name as groupName, p.month as month,p.year as year,p.payment_amounts as paymentAmounts from payment p inner join users u on u.id = p.user_id inner join groups g on g.id = p.group_id where p.user_id=:userId")
    Page<GetPaymentDto> findByUserId(@Param("userId") UUID userId,Pageable pageable);
}
