package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.ClassesRepository;
import com.ouiuo.timetable.mapper.ExcelParser;
import com.ouiuo.timetable.model.TrainingPair;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.List;

@Service
public class UpdateServiceImpl implements UpdateService {

    private static final String FILE_URL = "https://rsue.ru/studentam/raspisanie-zaochnaya-forma/doc/3/%D0%9F%D0%9C%D0%98OZ-311.xlsx";
    @Autowired
    private ExcelParser rowMapper;

    @Autowired
    private ClassesRepository classesRepository;


    @Override
    @SneakyThrows
    public void update() {
        BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
        List<TrainingPair> parse = rowMapper.parse(in);
        classesRepository.saveAll(parse);
    }
}
