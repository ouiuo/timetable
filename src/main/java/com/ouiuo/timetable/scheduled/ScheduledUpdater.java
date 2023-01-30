package com.ouiuo.timetable.scheduled;


import com.ouiuo.timetable.updater.GroupUpdateService;
import com.ouiuo.timetable.updater.TimetableUpdateService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdater {

    private final TimetableUpdateService timetableUpdateService;

    private final GroupUpdateService groupUpdateService;

    public ScheduledUpdater(TimetableUpdateService updateService, GroupUpdateService groupUpdateService) {
        this.timetableUpdateService = updateService;
        this.groupUpdateService = groupUpdateService;
    }

    @Scheduled(fixedRateString = "${app.scheduled.timetable.time}")
    public void updateTimetable() {
        timetableUpdateService.update();
    }


    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "${app.scheduled.groups.time}")
    public void updateGroups() {
        groupUpdateService.update();
    }

}
