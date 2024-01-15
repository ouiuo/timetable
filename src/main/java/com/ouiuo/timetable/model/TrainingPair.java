package com.ouiuo.timetable.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    //todo change string to ??
    private String classType;
    private Date startDate;
    private Date endDate;
    private Date updateDate;
}
