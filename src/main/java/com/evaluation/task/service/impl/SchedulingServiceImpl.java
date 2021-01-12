package com.evaluation.task.service.impl;

import com.evaluation.task.models.*;
import com.evaluation.task.service.IdentityService;
import com.evaluation.task.service.LookupService;
import com.evaluation.task.service.SchedulingService;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    private final Logger logger = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    @Value("${static.interview.limit}")
    private Integer INTERVIEW_LIMIT = 0;

    @Autowired
    private LookupService lookupService;
    @Autowired
    private IdentityService identityService;


    public List<Slots> getAvailableSlots(SearchCriteria query) throws Exception{

        Integer studentId = query.getStudentId();

        if(identityService.getInterviewCount(studentId) < INTERVIEW_LIMIT && lookupService.isStudentEligibile(studentId)){
            List<Integer> oldInterviewers = new ArrayList<>();
            try {
                oldInterviewers = lookupService.getAllInterviewsForStudent(studentId).stream().map(Interview::getInterviewerId).collect(Collectors.toList());
            }catch(Exception e){
                logger.info("No interviews conducted yet for the given Student ID");
            }
            return evaluateSlots(oldInterviewers, query.getStartDateTime(), query.getEndDateTime());
        }else{
            throw new Exception(String.format("Interview Limit of %d exhausted or Student ineligible.", INTERVIEW_LIMIT));
        }
    }

    private List<Slots> evaluateSlots(List<Integer> oldInterviewer, DateTime startTime, DateTime endTime){
        List<Availability> availableInterviews = lookupService.getInterviewersAvailability(null, oldInterviewer, startTime, endTime);

        if(availableInterviews.size() > 0){
            return IntStream.range(0, availableInterviews.size()).mapToObj(i -> createSlot(availableInterviews.get(i), i, identityService.getInterviewerById(availableInterviews.get(i).getInterviewerId()))).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    private Slots createSlot(Availability availability, Integer index, Interviewer interviewer){
        Slots slot = new Slots();

        slot.setId(index);
        slot.setEndtime(availability.getEndTime());
        slot.setInterviewerId(availability.getInterviewerId());
        String name = interviewer.getFirst_name() + " " + interviewer.getLast_name();
        slot.setInterviewerName(name);
        slot.setStartTime(availability.getStartTime());

        return slot;
    }
}
