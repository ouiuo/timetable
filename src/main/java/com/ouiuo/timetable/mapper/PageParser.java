package com.ouiuo.timetable.mapper;

import org.jsoup.nodes.Document;

import java.util.List;

public interface PageParser<T> {
    List<T> parse(Document document);
}
