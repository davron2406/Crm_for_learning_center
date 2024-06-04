package com.example.crm_for_learning_center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


public interface GetTeacherDto {
     String getFullName();
     UUID getId();
     double getSalary();
     int getGroupCount();
}
