package com.ouiuo.timetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "classes")
public class TrainingPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String className;
    private String lectureName;
    private String addressName;
    //todo change string to ??
    private String classType;
    private Date startDate;
    private Date endDate;
}
