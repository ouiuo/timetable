package com.ouiuo.timetable.updater;

import java.util.UUID;

public interface TimetableUpdateService {
    void update();

    void update(UUID groupId);
}
