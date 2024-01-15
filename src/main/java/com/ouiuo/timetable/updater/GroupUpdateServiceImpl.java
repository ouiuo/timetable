package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.GroupRepository;
import com.ouiuo.timetable.dao.UserRepository;
import com.ouiuo.timetable.mapper.PageParser;
import com.ouiuo.timetable.model.Group;
import com.ouiuo.timetable.model.User;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupUpdateServiceImpl implements GroupUpdateService {

    private static final StringBuilder FILE_URL = new StringBuilder("https://rsue.ru/studentam/raspisanie-zaochnaya-forma/");

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private final PageParser rsueZOPageParser;

    public GroupUpdateServiceImpl(UserRepository userRepository, GroupRepository groupRepository, PageParser rsueZOPageParser) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.rsueZOPageParser = rsueZOPageParser;
    }


    public void updateAll() {
        List<User> distinctGroup = userRepository.findAll();
    }

    @Override
    @SneakyThrows
    @Transactional
    public void update() {
        updateAll();

        String url = "https://rsue.ru/studentam/raspisanie-zaochnaya-forma/";
        WebClient webClient = WebClient.builder().build();

        webClient.get();
        Document document = Jsoup.connect(url).get();
        List<Group> groups = rsueZOPageParser.parse(document);
        List<Group> all = groupRepository.findAll();

        List<Group> groups1 = new ArrayList<>(groups);
        groups1.removeAll(all);
        groupRepository.saveAll(groups1);


    }
}
