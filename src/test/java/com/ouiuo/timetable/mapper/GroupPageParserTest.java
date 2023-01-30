package com.ouiuo.timetable.mapper;

import com.ouiuo.timetable.model.Group;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.io.File;
import java.util.List;

class GroupPageParserTest {
    private static Document document;

    private final static String fileName = "html/timetablesZO.html";
    private GroupPageParser groupPageParser = new GroupPageParser();


    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        File htmlPage = new File(GroupPageParserTest.class.getClassLoader().getResource(fileName).getFile());
        document = Jsoup.parse(htmlPage);
    }

    @SneakyThrows
    @Test
    public void check() {
        List<Group> parse = groupPageParser.parse(document);
        int expected = 492;
        Assert.isTrue(parse.size() == expected, "Check group count, expected %d actual %d".formatted(expected, parse.size()));
        List<Group> groups = parse.stream().filter(e -> e.getGroupName().equals("ТДZ") && e.getGroupNumber() == 261 && e.isPracticum()).toList();
        Assert.isTrue(groups.size() == 1, "Check group count, expected %d actual %d".formatted(1, groups.size()));
        groups = parse.stream().filter(e -> e.getGroupName().equals("ТДZ") && e.getGroupNumber() == 261 && !e.isPracticum()).toList();
        Assert.isTrue(groups.size() == 1, "Check group count, expected %d actual %d".formatted(1, groups.size()));
    }


}