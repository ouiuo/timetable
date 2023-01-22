package com.ouiuo.timetable.model;

import lombok.Data;

import java.util.Date;

@Data
public class TrainingPair {
    private String className;
    private String lectureName;
    private String addressName;
    //todo change string to ??
    private String classType;
    private Date startDate;
    private Date endDate;
}
