package com.ouiuo.timetable.updater;

import com.ouiuo.timetable.dao.GroupRepository;
import com.ouiuo.timetable.dao.UrlConfigRepository;
import com.ouiuo.timetable.dao.UsersRepository;
import com.ouiuo.timetable.mapper.PageParser;
import com.ouiuo.timetable.model.Group;
import com.ouiuo.timetable.model.UrlConfig;
import com.ouiuo.timetable.model.User;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ouiuo.timetable.model.enums.UrlService.RSUE_ZO;

@Service
public class GroupUpdateServiceImpl implements GroupUpdateService {

    private static final StringBuilder FILE_URL = new StringBuilder("https://rsue.ru/studentam/raspisanie-zaochnaya-forma/");

    private final UsersRepository usersRepository;
    private final UrlConfigRepository urlConfigRepository;
    private final GroupRepository groupRepository;

    private final PageParser rsueZOPageParser;

    public GroupUpdateServiceImpl(UsersRepository usersRepository, UrlConfigRepository urlConfigRepository, GroupRepository groupRepository, PageParser rsueZOPageParser) {
        this.usersRepository = usersRepository;
        this.urlConfigRepository = urlConfigRepository;
        this.groupRepository = groupRepository;
        this.rsueZOPageParser = rsueZOPageParser;
    }


    public void updateAll() {
        List<User> distinctGroup = usersRepository.findDistinctByGroup();
        System.out.println(distinctGroup.get(0).getGroup().getId());
        System.out.println("===================================");
    }

    @Override
    @SneakyThrows
    public void update() {
        updateAll();
        Optional<UrlConfig> rsueZoConfig = urlConfigRepository.findByCode(RSUE_ZO.getCode());
        if (rsueZoConfig.isPresent() || true) {
            //String url = rsueZoConfig.get().getUrl();
            String url = "https://rsue.ru/studentam/raspisanie-zaochnaya-forma/";
            Document document = Jsoup.connect(url).get();
            List groups = rsueZOPageParser.parse(document);
            groupRepository.saveAll(groups);
        }

    }
}
