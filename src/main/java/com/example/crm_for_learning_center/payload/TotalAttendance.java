package com.example.crm_for_learning_center.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalAttendance {
    private int attendanceCount;
    private int allLessonsCount;
    private float averageHomework;
    private UUID userId;
    private String fullName;

}
