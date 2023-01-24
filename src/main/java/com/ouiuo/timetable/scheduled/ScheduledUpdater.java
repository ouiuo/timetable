package com.ouiuo.timetable.scheduled;


import com.ouiuo.timetable.updater.UpdateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledUpdater {

    @Autowired
    private UpdateServiceImpl updateService;

    @Scheduled(fixedRateString = "${app.scheduled.time}")
    public void update() {
        updateService.update();
    }

}
