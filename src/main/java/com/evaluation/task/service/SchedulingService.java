package com.evaluation.task.service;

import com.evaluation.task.models.SearchCriteria;
import com.evaluation.task.models.Slots;

import java.util.List;

public interface SchedulingService {

    List<Slots> getAvailableSlots(SearchCriteria query) throws Exception;
}
