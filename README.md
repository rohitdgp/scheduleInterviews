# ScheduleInterviews

A small snippet to replicate the online Slot booking system (Mock interview for Students). Interviewers and students can register themselves, Interviewers can enter their availability slots according to which studenst can book their respective suitable slots.

### Constraints
* Student is allowed to book a max of 15 INTERVIEWS.
* Student is only allowed to view slots if he has not given any interviews or if the grades received in the last two interviews are > 1. (grade ranges from 0 - 5)
* Student can only book a slot with an interviewer whom he has never interviewd with.

### Models
* Interviewer
* Student
* Schedules
* Availability
* Interview

## Instructions

Build/Install the app using Maven. (mvn install)
Once installed target folder will contain a jar file.

Run the jar file using java command - `java -jar <filename>.jar`

## TechStacks
* JAVA - Springboot
* H2 Database (in mem)
