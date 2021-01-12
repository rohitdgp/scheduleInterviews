package com.evaluation.task.repository;

import com.evaluation.task.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DataRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Interviewer> fetchInterviewer(Integer interviewerId){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Interviewer> cq = cb.createQuery(Interviewer.class);
        Root<Interviewer> root = cq.from(Interviewer.class);

        if(interviewerId!=null)
            cq.select(root).where(cb.equal(root.get("interviewerId"), interviewerId));
        else cq.select(root);

        Query<Interviewer> query = currentSession.createQuery(cq);
        return query.getResultList();
    }

    public void addInterviewer(Interviewer interviewer){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(interviewer);
        tx.commit();
        session.close();
    }

    public List<Student> fetchStudent(Integer studentId){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);

        if(studentId!=null)
            cq.select(root).where(cb.equal(root.get("studentId"), studentId));
        else cq.select(root);

        Query<Student> query = currentSession.createQuery(cq);
        return query.getResultList();
    }

    public void addStudent(Student student){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.save(student);
        tx.commit();
        session.close();
    }

    public List<Availability> getAvailability(Integer interviewerId){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Availability> cq = cb.createQuery(Availability.class);
        Root<Availability> root = cq.from(Availability.class);

        Predicate dateFilter = cb.greaterThanOrEqualTo(root.get("startTime"), new Date());

        Predicate finalFilter = interviewerId==null ? dateFilter : cb.and(cb.equal(root.get("interviewerId"), interviewerId), dateFilter);

        cq.select(root).where(finalFilter);

        Query<Availability> query = currentSession.createQuery(cq);
        return query.getResultList();
    }

    public void addAvailability(Availability availability){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(availability);
        tx.commit();
        session.close();
    }

    public List<Schedule> getSchedules(Integer interviewerId, Integer studentId){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
        Root<Schedule> root = cq.from(Schedule.class);

        Predicate interviewerFilter = interviewerId==null? null : cb.equal(root.get("interviewerId"), interviewerId);
        Predicate studentFilter = studentId==null? null : cb.equal(root.get("studentId"), studentId);
        Predicate dateFilter = cb.greaterThanOrEqualTo(root.get("startTime"), new Date());

        Predicate finalFilter = interviewerFilter==null ? (studentFilter==null ? dateFilter : cb.and(studentFilter, dateFilter)) :
                ((studentFilter==null ? cb.and(interviewerFilter ,dateFilter) : cb.and(interviewerFilter,cb.and(studentFilter, dateFilter))));

        cq.select(root).where(finalFilter);

        Query<Schedule> query = currentSession.createQuery(cq);
        return query.getResultList();
    }

    public void addSchedule(Schedule schedule){
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(schedule);
        tx.commit();
        session.close();
    }

    public List<Availability> getAvailabilityBySearch(List<Integer> inInterviewerId, List<Integer> exInterviewerId, DateTime startTime, DateTime endTime){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Availability> cq = cb.createQuery(Availability.class);

        Root<Availability> root = cq.from(Availability.class);

        Predicate includeInterviewer = (inInterviewerId!=null && inInterviewerId.size()>0 )? root.get("interviewerId").in(inInterviewerId) : null;
        Predicate excludeInterviewer = (exInterviewerId!=null && exInterviewerId.size()>0 )? cb.not(root.get("interviewerId").in(exInterviewerId)) : null;
        Predicate dateTime = cb.and(cb.and(cb.lessThanOrEqualTo(root.get("startTime"), startTime.toDate()), cb.greaterThanOrEqualTo(root.get("endTime"), startTime.toDate()) )
                , cb.and(cb.lessThanOrEqualTo(root.get("startTime"), endTime.toDate()), cb.greaterThanOrEqualTo(root.get("endTime"), endTime.toDate()) ) );

        Predicate finalFilter = includeInterviewer==null ? (excludeInterviewer==null ? dateTime : cb.and(excludeInterviewer, dateTime)) :
                ((excludeInterviewer==null ? cb.and(includeInterviewer ,dateTime) : cb.and(includeInterviewer,cb.and(excludeInterviewer, dateTime))));

        cq.select(root).where(finalFilter);

        Query<Availability> query = currentSession.createQuery(cq);
        return query.getResultList();
    }

    public Integer interviewCount(Integer interviewerId, Integer studentId){

        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Interview> root = cq.from(Interview.class);

        Predicate interviewerFilter = interviewerId==null? null : cb.equal(root.get("interviewerId"), interviewerId);
        Predicate studentFilter = studentId==null? null : cb.equal(root.get("studentId"), studentId);

        Predicate finalFilter = interviewerFilter==null ? (studentFilter) :
                ((studentFilter==null ? interviewerFilter: cb.and(interviewerFilter,studentFilter)));

        if(finalFilter!=null)
            cq.select(cb.count(root)).where(finalFilter);
        else cq.select(cb.count(root));

        Query<Long> query = currentSession.createQuery(cq);
        return query.getResultList().get(0).intValue();

    }

    public List<Integer> getInterviewGrades(Integer interviewerId, Integer studentId, Integer top, Integer bottom){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();

        CriteriaQuery<Interview> cq = cb.createQuery(Interview.class);
        Root<Interview> root = cq.from(Interview.class);

        Predicate interviewerFilter = interviewerId==null? null : cb.equal(root.get("interviewerId"), interviewerId);
        Predicate studentFilter = studentId==null? null : cb.equal(root.get("studentId"), studentId);

        Predicate finalFilter = interviewerFilter==null ? (studentFilter) :
                ((studentFilter==null ? interviewerFilter: cb.and(interviewerFilter,studentFilter)));

        if(finalFilter!=null)
            cq.select(root).where(finalFilter);
        else cq.select(root);
        Integer maxResults = 1000;

        if(top!=null){
            maxResults = top;
            cq.orderBy(cb.desc(root.get("id")));
        }else{
            maxResults = bottom;
            cq.orderBy(cb.asc(root.get("id")));
        }

        Query<Interview> query = currentSession.createQuery(cq).setMaxResults(maxResults);
        try {
            return query.getResultList().stream().map(Interview::getGrade).collect(Collectors.toList());
        }catch(Exception e){
            return new ArrayList<>();
        }
    }

}
