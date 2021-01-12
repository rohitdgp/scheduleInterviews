package com.evaluation.task.controller;

import com.evaluation.task.models.Interviewer;
import com.evaluation.task.models.Student;
import com.evaluation.task.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class IdentityController {
    private final Logger logger = LoggerFactory.getLogger(SchedulingController.class);

    @Autowired
    private IdentityService identityService;

    @RequestMapping(value = "/getAllInterviewers", method = RequestMethod.GET)
    public List<Interviewer> getAllInterviewers() throws Exception{
        return identityService.getAllInterviewers();
    }

    @RequestMapping(value = "/getInterviewerById", method = RequestMethod.GET)
    public Interviewer getInterviewerById(@RequestParam("interviewerId") Integer interviewerId) throws Exception{
        return identityService.getInterviewerById(interviewerId);
    }

    @RequestMapping(value = "/addInterviewer", method = RequestMethod.POST)
    public String addInterviewer(@RequestBody Interviewer interviewer) throws Exception{
        identityService.addInterviewer(interviewer);
        return "Added Successfully";
    }

    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public List<Student> getAllStudents() throws Exception{
        return identityService.getAllStudents();
    }

    @RequestMapping(value = "/getStudentById", method = RequestMethod.GET)
    public Student getStudentById(@RequestParam("interviewerId") Integer studentId) throws Exception{
        return identityService.getStudentById(studentId);
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public String addStudent(@RequestBody Student student) throws Exception{
        identityService.addStudent(student);
        return "Added Successfully";
    }
}
