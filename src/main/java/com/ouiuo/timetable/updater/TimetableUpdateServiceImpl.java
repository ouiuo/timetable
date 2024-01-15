package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.ClassesRepository;
import com.ouiuo.timetable.dao.UserRepository;
import com.ouiuo.timetable.mapper.ExcelParser;
import com.ouiuo.timetable.model.Group;
import com.ouiuo.timetable.model.TrainingPair;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableUpdateServiceImpl implements TimetableUpdateService {

    private static final String FILE_URL = "https://rsue.ru/studentam/raspisanie-zaochnaya-forma/";
    private final ExcelParser<TrainingPair> rowMapper;
    private final ClassesRepository classesRepository;
    private final UserRepository userRepository;


    @Override
    @SneakyThrows
    @Transactional
    public void update() {
        List<Group> distinctGroups = userRepository.findDistinctGroups();
        for (Group group : distinctGroups) {
            URL url = new URL(FILE_URL + group.getUrl());
            URLConnection urlConnection = url.openConnection();
            urlConnection.addRequestProperty("User-Agent",
                    "TG Bot @rsue_timetable_bot");

            BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
            List<TrainingPair> parse = rowMapper.parse(in, group);
            if (parse.size() > 0) {
                classesRepository.deleteByGroupId(group.getId());
            }
            classesRepository.saveAll(parse);
        }

    }
}
