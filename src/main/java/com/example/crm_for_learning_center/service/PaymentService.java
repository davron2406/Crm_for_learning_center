package com.example.crm_for_learning_center.service;

import com.example.crm_for_learning_center.entity.Payment;
import com.example.crm_for_learning_center.entity.User;
import com.example.crm_for_learning_center.payload.ApiResponse;
import com.example.crm_for_learning_center.payload.GetPaymentDto;
import com.example.crm_for_learning_center.payload.PaymentDto;
import com.example.crm_for_learning_center.repository.PaymentRepository;
import com.example.crm_for_learning_center.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse addPayment(PaymentDto paymentDto) {
        Optional<Payment> byUserIdAndAndGroupIdAndMonthAndYear = paymentRepository.findByUserIdAndAndGroupIdAndMonthAndYear(
                paymentDto.getUserId(),
                paymentDto.getGroupId(),
                paymentDto.getMonth(),
                paymentDto.getYear()
        );

        if(byUserIdAndAndGroupIdAndMonthAndYear.isPresent()) {
            Payment payment = byUserIdAndAndGroupIdAndMonthAndYear.get();
            List<Float> payments = payment.getPaymentAmounts();
            payments.add(paymentDto.getPaymentAmount());
            payment.setPaymentAmounts(payments);
            paymentRepository.save(payment);
        }
        else{
            Payment payment = new Payment();
            payment.setUserId(paymentDto.getUserId());
            payment.setGroupId(paymentDto.getGroupId());
            payment.setMonth(paymentDto.getMonth());
            payment.setYear(paymentDto.getYear());
            payment.setPaymentAmounts(Arrays.asList(paymentDto.getPaymentAmount()));
            paymentRepository.save(payment);
        }

        return new ApiResponse("Payment saved successfully",true);
    }

    public Page<GetPaymentDto> getPayments(Pageable pageable) {
       return paymentRepository.findAllPaymentsWithUserNamesAndGroupName(pageable);
    }

    public Page<GetPaymentDto> getUserPayments(String email, Pageable pageable) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.map(user -> paymentRepository.findByUserId(user.getId(), pageable)).orElse(null);
    }
}
