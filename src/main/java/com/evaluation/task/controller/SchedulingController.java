package com.evaluation.task.controller;

import com.evaluation.task.models.SearchCriteria;
import com.evaluation.task.models.Slots;
import com.evaluation.task.service.SchedulingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class SchedulingController {

    private final Logger logger = LoggerFactory.getLogger(SchedulingController.class);

    @Autowired
    private SchedulingService schedulingService;

    @RequestMapping(value = "/getSchedules", method = RequestMethod.POST)
    public List<Slots> getSchedules(@RequestBody SearchCriteria query) throws Exception{
        return schedulingService.getAvailableSlots(query);
    }


}
