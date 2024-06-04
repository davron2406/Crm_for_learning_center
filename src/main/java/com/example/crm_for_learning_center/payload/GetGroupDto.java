package com.example.crm_for_learning_center.payload;

import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public interface GetGroupDto {

    String getName();
    UUID getId();
    Date getStartDate();
    LocalTime getStartTime();
}
