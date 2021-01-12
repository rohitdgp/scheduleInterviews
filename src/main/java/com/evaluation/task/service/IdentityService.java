package com.evaluation.task.service;

import com.evaluation.task.models.Interviewer;
import com.evaluation.task.models.Student;

import java.util.List;

public interface IdentityService {

    List<Student> getAllStudents();
    List<Interviewer> getAllInterviewers();
    Student getStudentById(Integer id);
    void addStudent(Student student);
    Interviewer getInterviewerById(Integer id);
    void addInterviewer(Interviewer interviewer);
    Integer getInterviewCount(Integer studentId);
    Integer getInterviewCountInt(Integer interviewerId);
}
