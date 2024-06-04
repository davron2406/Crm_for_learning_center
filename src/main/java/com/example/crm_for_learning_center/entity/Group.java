package com.example.crm_for_learning_center.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "groups")
public class Group extends AbstractEntity {
    private String name;

    private Date startDate;

    private LocalTime startTime;

    @ManyToMany
    private List<Teacher> teacher;

    @ManyToMany
    private List<User> users;

}
