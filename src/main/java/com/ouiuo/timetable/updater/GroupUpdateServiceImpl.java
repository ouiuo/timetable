package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.GroupRepository;
import com.ouiuo.timetable.mapper.PageParser;
import com.ouiuo.timetable.model.Group;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupUpdateServiceImpl implements GroupUpdateService {

    @Value(value = "${timetable.zo.url}")
    private final String FILE_URL;

    private final GroupRepository groupRepository;

    private final PageParser<Group> rsueZOPageParser;


    @Override
    @SneakyThrows
    public void update() {
        Document document = Jsoup.connect(FILE_URL).get();
        List<Group> groups = rsueZOPageParser.parse(document);
        groups.forEach(group -> {
            log.debug("Find Group By Name And Number {} {}", group.getGroupName(), group.getGroupNumber());
            Optional<Group> groupByGroupNameAndGroupNumber = groupRepository.findGroupByGroupNameAndGroupNumberAndIsPracticum(group.getGroupName(), group.getGroupNumber(), group.isPracticum());
            groupByGroupNameAndGroupNumber.ifPresent(value -> group.setId(value.getId()));
        });
        groupRepository.saveAll(groups);
    }
}
