package com.evaluation.task.models;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import javax.persistence.Entity;
import java.sql.Date;
import java.sql.Timestamp;

public class SearchCriteria {

    public Integer studentId;

    public DateTime startDateTime;

    public DateTime endDateTime;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public DateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(DateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public DateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(DateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
