package com.evaluation.task.controller;

import com.evaluation.task.models.Availability;
import com.evaluation.task.models.Interviewer;
import com.evaluation.task.models.Schedule;
import com.evaluation.task.models.Student;
import com.evaluation.task.service.IdentityService;
import com.evaluation.task.service.LookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class LookupController {
    private final Logger logger = LoggerFactory.getLogger(SchedulingController.class);

    @Autowired
    private LookupService lookupService;

    @RequestMapping(value = "/getScheduledInterviews", method = RequestMethod.GET)
    public List<Schedule> getScheduledInterviews() throws Exception{
        return lookupService.getAllSchedules();
    }

    @RequestMapping(value = "/getScheduleForStudent", method = RequestMethod.GET)
    public List<Schedule> getScheduleForStudent(@RequestParam("studentId") Integer studentId) throws Exception{
        return lookupService.getAllSchedulesForStudent(studentId);
    }

    @RequestMapping(value = "/getScheduleForInterviewer", method = RequestMethod.GET)
    public List<Schedule> getScheduleForInterviewer(@RequestParam("interviewerId") Integer interviewerId) throws Exception{
        return lookupService.getAllSchedulesForInterviewer(interviewerId);
    }

    @RequestMapping(value = "/getInterviewerAvailability", method = RequestMethod.GET)
    public List<Availability> getInterviewerAvailability(@RequestParam("interviewerId") Integer interviewerId) throws Exception{
        return lookupService.getInterviewerAvailability(interviewerId);
    }

    @RequestMapping(value = "/addAvailability", method = RequestMethod.POST)
    public String addAvailability(@RequestBody Availability availability) throws Exception{
        lookupService.addAvailability(availability);
        return "Added Successfully";
    }
}
