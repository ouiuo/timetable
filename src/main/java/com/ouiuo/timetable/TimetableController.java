package com.ouiuo.timetable;

import com.ouiuo.timetable.updater.TimetableUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("timetable/api")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableUpdateService timetableUpdateService;

    @PostMapping("/group/add")
    public ResponseEntity<String> updateTimetableByGroup(@RequestParam
                                                         UUID groupId) {
        timetableUpdateService.update(groupId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
