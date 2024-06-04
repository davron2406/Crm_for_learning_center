package com.example.crm_for_learning_center.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbstractEntity{
    private UUID userId;
    private UUID groupId;
    private List<Float> paymentAmounts;
    private int month;
    private int year;
}
