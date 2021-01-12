package com.evaluation.task.service;

import com.evaluation.task.models.Availability;
import com.evaluation.task.models.Interview;
import com.evaluation.task.models.Schedule;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface LookupService {

    List<Schedule> getAllSchedules();
    List<Schedule> getAllSchedulesForStudent(Integer id);
    List<Schedule> getAllSchedulesForInterviewer(Integer id);
    List<Availability> getInterviewerAvailability(Integer interviewerId);
    List<Availability> getInterviewersAvailability(List<Integer> inInterviewerId, List<Integer> exInterviewerId, DateTime startTime, DateTime endTime);
    void addAvailability(Availability availability);
    List<Interview> getAllInterviewsForStudent(Integer studentId);
    Boolean isStudentEligibile(Integer studentId);
}
