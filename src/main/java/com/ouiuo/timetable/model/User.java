package com.ouiuo.timetable.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "users")
public class User {
    @Id
    private Long id;

    private Date last;

    private Integer numbers;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    private Group group;

    public void updateLast() {
        numbers++;
        last = new Date();
    }

    public User() {

    }
}
