package com.ouiuo.timetable.mapper;

import java.io.FilterInputStream;
import java.util.List;

public interface ExcelParser<T> {
    List<T> parse(FilterInputStream filterInputStream);
}
