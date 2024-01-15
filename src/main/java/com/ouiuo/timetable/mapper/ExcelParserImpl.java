package com.ouiuo.timetable.mapper;

import com.google.common.collect.Streams;
import com.ouiuo.timetable.model.Group;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FilterInputStream;
import java.util.List;

public abstract class ExcelParserImpl<T> implements ExcelParser<T> {

    private final int START_ROW_NUMBER;

    public ExcelParserImpl() {
        START_ROW_NUMBER = 0;
    }
    public ExcelParserImpl(int startRowNumber) {
        START_ROW_NUMBER = startRowNumber;
    }


    private int getStartRowNumber() {
        return 0;
    }
    public abstract boolean isBreakRow(Row row);
    public abstract T map(Row row, Group group);

    @Override
    @SneakyThrows
    public List<T> parse(FilterInputStream filterInputStream, Group group) {
        Workbook workbook = new XSSFWorkbook(filterInputStream);

        return Streams.stream(workbook.getSheetAt(0).iterator()).filter(row -> {
            return row.getRowNum() >= START_ROW_NUMBER && !isBreakRow(row);
        }).map(row -> map(row, group)).toList();
    }
}
