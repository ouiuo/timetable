package com.ouiuo.timetable.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UrlConfig {
    @Id
    private Long id;

    private Integer code;

    private String url;
}
