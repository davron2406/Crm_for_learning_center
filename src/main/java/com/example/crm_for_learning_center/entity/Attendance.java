package com.example.crm_for_learning_center.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Attendance extends AbstractEntity{

    
    private UUID userId;

    private UUID groupId;

    private LocalDate date;

    private boolean isPresent;

    private int homework;

}
