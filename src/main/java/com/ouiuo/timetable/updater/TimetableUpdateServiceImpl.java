package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.ClassesRepository;
import com.ouiuo.timetable.dao.GroupRepository;
import com.ouiuo.timetable.mapper.ExcelParser;
import com.ouiuo.timetable.model.Group;
import com.ouiuo.timetable.model.TrainingPair;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.List;

@Service
public class TimetableUpdateServiceImpl implements TimetableUpdateService {

    private static final String FILE_URL = "https://rsue.ru/studentam/raspisanie-zaochnaya-forma/doc/3/%D0%9F%D0%9C%D0%98OZ-311.xlsx";

    private final ExcelParser<TrainingPair> rowMapper;

    private final ClassesRepository classesRepository;

    private final GroupRepository groupRepository;


    public TimetableUpdateServiceImpl(ExcelParser<TrainingPair> rowMapper, ClassesRepository classesRepository, GroupRepository groupRepository) {
        this.rowMapper = rowMapper;
        this.classesRepository = classesRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    @SneakyThrows
    public void update() {
        BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
        List<TrainingPair> parse = rowMapper.parse(in);
        if (parse.size() > 0) {
            classesRepository.deleteAll();
        }
        classesRepository.saveAll(parse);
    }
}
