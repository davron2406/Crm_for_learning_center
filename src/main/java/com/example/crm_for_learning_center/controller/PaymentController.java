package com.example.crm_for_learning_center.controller;

import com.example.crm_for_learning_center.payload.PaymentDto;
import com.example.crm_for_learning_center.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    @PreAuthorize(value = "hasAuthority('ADD_GROUP')")
    public HttpEntity<?> addGroup(@RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentService.addPayment(paymentDto));
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('ADD_GROUP')")
    public HttpEntity<?> getPayments(Pageable pageable){
        return ResponseEntity.ok(paymentService.getPayments(pageable));
    }

    @GetMapping("/getUserPayments/{email}")
    @PreAuthorize(value = "hasAuthority('ADD_GROUP')")
    public HttpEntity<?> getUserPayments(@PathVariable String email, Pageable pageable){
        return ResponseEntity.ok(paymentService.getUserPayments(email,pageable));
    }

}
