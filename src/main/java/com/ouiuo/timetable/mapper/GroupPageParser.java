package com.ouiuo.timetable.mapper;

import com.ouiuo.timetable.model.Group;
import jakarta.transaction.NotSupportedException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class GroupPageParser implements PageParser<Group> {
    @Override
    public List<Group> parse(Document document) {
        Stream<Group> groupStream = document.getElementsByAttributeValueStarting("href", "doc/").stream()
                .map(element -> {
                    String groupNameNumber = element.childNode(0).attr("#text");
                    String url = element.attr("href");
                    Group group;
                    try {
                        group = new Group(groupNameNumber, url);
                    } catch (NotSupportedException e) {
                        return null;
                    }
                    return group;
                }).filter(Objects::nonNull);
        return groupStream.toList();
    }
}
