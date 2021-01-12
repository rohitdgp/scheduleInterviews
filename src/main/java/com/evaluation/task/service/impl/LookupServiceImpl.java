package com.evaluation.task.service.impl;

import com.evaluation.task.models.*;
import com.evaluation.task.repository.DataRepository;
import com.evaluation.task.service.LookupService;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class LookupServiceImpl implements LookupService {

    private final Logger logger = LoggerFactory.getLogger(LookupServiceImpl.class);

    @Autowired
    private DataRepository dataRepository;

    public List<Schedule> getAllSchedules(){
        return dataRepository.getSchedules(null, null);
    }

    public List<Schedule> getAllSchedulesForStudent(Integer id){
        return dataRepository.getSchedules(null, id);
    }

    public List<Schedule> getAllSchedulesForInterviewer(Integer id){
        return dataRepository.getSchedules(id, null);
    }

    public List<Availability> getInterviewerAvailability(Integer interviewerId){
        return dataRepository.getAvailability(interviewerId);
    }

    public List<Availability> getInterviewersAvailability(List<Integer> inInterviewerId, List<Integer> exInterviewerId, DateTime startTime, DateTime endTime){

        return dataRepository.getAvailabilityBySearch(inInterviewerId, exInterviewerId, startTime, endTime);
    }

    public void addAvailability(Availability availability){
        dataRepository.addAvailability(availability);
    }

    public List<Interview> getAllInterviewsForStudent(Integer studentId){

        return new ArrayList<>();
    }

    public Boolean isStudentEligibile(Integer studentId){
        List<Integer> lastTwoGrades = dataRepository.getInterviewGrades(null, studentId, null, 2);
        return lastTwoGrades.size()<=1 || ( lastTwoGrades.get(0)>1 && lastTwoGrades.get(1) > 1);
    }
}
