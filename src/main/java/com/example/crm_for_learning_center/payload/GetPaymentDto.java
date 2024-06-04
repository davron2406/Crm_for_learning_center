package com.example.crm_for_learning_center.payload;

import java.util.List;
import java.util.UUID;

public interface GetPaymentDto {
    UUID getId();
    String getFullName();
    String getGroupName();
    Integer getMonth();
    Integer getYear();
    List<Float> getPaymentAmounts();
}
