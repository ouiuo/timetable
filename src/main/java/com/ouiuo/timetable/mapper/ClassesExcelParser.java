package com.ouiuo.timetable.mapper;

import com.ouiuo.timetable.model.TrainingPair;
import org.apache.commons.math3.util.Pair;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ClassesExcelParser extends ExcelParserImpl<TrainingPair> {

    public ClassesExcelParser() {
        super(15);
    }
    @Override
    public boolean isBreakRow(Row row) {
        String st = row.getCell(0).getStringCellValue();
        return st.isEmpty() || st.isBlank();
    }

    @Override
    public TrainingPair map(Row row) {
        TrainingPair trainingPair = new TrainingPair();

        trainingPair.setClassName(row.getCell(1).getStringCellValue());
        trainingPair.setLectureName(row.getCell(2).getStringCellValue());
        trainingPair.setClassType(row.getCell(3).getStringCellValue());
        trainingPair.setAddressName(row.getCell(5).getStringCellValue());

        Pair<Date, Date> classSegment = parseDate(row);
        trainingPair.setStartDate(classSegment.getFirst());
        trainingPair.setEndDate(classSegment.getSecond());

        return trainingPair;
    }

    private Pair<Date, Date> parseDate(Row row) {
        List<Integer> dayMounth = Arrays.stream(row.getCell(0).getStringCellValue().split("\\.")).map(s -> Integer.valueOf(s.substring(0, 2))).toList();
        int year = Year.now().getValue();
        int mounth = dayMounth.get(1) - 1;
        int day = dayMounth.get(0);
        List<Integer> integers = Arrays.stream(row.getCell(4).getStringCellValue().split("-"))
                .flatMap(Pattern.compile("\\.")::splitAsStream)
                .map(Integer::valueOf).toList();
        Date startDate = new GregorianCalendar(year, mounth, day, integers.get(0), integers.get(1)).getTime();
        Date endDate = new GregorianCalendar(year, mounth, day, integers.get(2), integers.get(3)).getTime();
        return new Pair<>(startDate, endDate);
    }

}
