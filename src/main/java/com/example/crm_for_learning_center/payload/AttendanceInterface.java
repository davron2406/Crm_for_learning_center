package com.example.crm_for_learning_center.payload;


import java.util.UUID;

public interface AttendanceInterface {
    UUID getUserId();
    UUID getGroupId();
    boolean getPresent();
    int getHomework();

}
