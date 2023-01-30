package com.ouiuo.timetable.model.enums;

public enum UrlService {
    RSUE_ZO(100)
    ;
    private int code;

    public int getCode() {
        return code;
    }
    private UrlService(int code) {
        this.code = code;
    }
}
