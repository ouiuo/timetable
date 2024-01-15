package com.ouiuo.timetable.mapper;

import com.ouiuo.timetable.model.Group;

import java.io.FilterInputStream;
import java.util.List;

public interface ExcelParser<T> {
    List<T> parse(FilterInputStream filterInputStream, Group group);
}
