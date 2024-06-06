package com.example.crm_for_learning_center.payload;

import java.util.List;
import java.util.UUID;

public interface GroupUsersWithPayment {
     String getFullName();
     String getEmail();
     UUID getId();
     List<Float> getPaymentAmounts();
}
