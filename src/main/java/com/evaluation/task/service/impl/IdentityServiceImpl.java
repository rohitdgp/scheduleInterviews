package com.evaluation.task.service.impl;

import com.evaluation.task.models.Interviewer;
import com.evaluation.task.models.Student;
import com.evaluation.task.repository.DataRepository;
import com.evaluation.task.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdentityServiceImpl implements IdentityService {

    @Autowired
    private DataRepository dataRepository;

    public List<Student> getAllStudents(){

        return dataRepository.fetchStudent(null);
    }

    public List<Interviewer> getAllInterviewers(){
        return dataRepository.fetchInterviewer(null);
    }

    public Student getStudentById(Integer id){
        return dataRepository.fetchStudent(id).get(0);
    }

    public void addStudent(Student student){
        dataRepository.addStudent(student);
    }

    public Interviewer getInterviewerById(Integer id){
        return dataRepository.fetchInterviewer(id).get(0);
    }

    public void addInterviewer(Interviewer interviewer){
        dataRepository.addInterviewer(interviewer);
    }

    public Integer getInterviewCount(Integer studentId){
        return dataRepository.interviewCount(null, studentId);
    }

    public Integer getInterviewCountInt(Integer interviewerId){
        return dataRepository.interviewCount(interviewerId, null);
    }
}
