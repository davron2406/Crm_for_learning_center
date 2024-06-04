package com.example.crm_for_learning_center.payload;

import com.example.crm_for_learning_center.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private String name;
    private String startDate;
    private String startTime;

    private UUID teacherId;
    private List<UUID> studentIds;
}
