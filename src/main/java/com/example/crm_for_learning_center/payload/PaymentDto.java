package com.example.crm_for_learning_center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private UUID userId;
    private UUID groupId;
    private float paymentAmount;
    private int month;
    private int year;
}
