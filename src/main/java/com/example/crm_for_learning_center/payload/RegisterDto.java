package com.example.crm_for_learning_center.payload;

import lombok.Data;

@Data
public class RegisterDto {

    private String fullName;

    private String email;
    private String password;
}
