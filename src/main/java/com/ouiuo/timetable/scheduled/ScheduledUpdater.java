package com.ouiuo.timetable.scheduled;


import com.ouiuo.timetable.updater.GroupUpdateService;
import com.ouiuo.timetable.updater.TimetableUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledUpdater {

    private final TimetableUpdateService timetableUpdateService;

    private final GroupUpdateService groupUpdateService;

    @Scheduled(fixedRateString = "${app.scheduled.timetable.time}")
    public void updateTimetable() {
        timetableUpdateService.update();
    }


    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(fixedRateString = "${app.scheduled.groups.time}")
    public void updateGroups() {
        groupUpdateService.update();
    }

}
