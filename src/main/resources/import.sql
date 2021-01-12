DROP TABLE IF EXISTS interviewer;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS availability;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS interviews;

CREATE TABLE student (studentId INT AUTO_INCREMENT  PRIMARY KEY,  first_name VARCHAR(250) NOT NULL,  last_name VARCHAR(250) NOT NULL);

CREATE TABLE interviewer (  interviewerId INT AUTO_INCREMENT  PRIMARY KEY,  first_name VARCHAR(250) NOT NULL,  last_name VARCHAR(250) NOT NULL);

CREATE TABLE availability (  id INT AUTO_INCREMENT  PRIMARY KEY,  interviewerId INT ,  startTime TIMESTAMP,  endTime TIMESTAMP);

CREATE TABLE schedules (  id INT AUTO_INCREMENT PRIMARY KEY,  studentId INT,  interviewerId INT,  startTime TIMESTAMP,  endTime TIMESTAMP);

CREATE TABLE interviews (  id INT AUTO_INCREMENT PRIMARY KEY,  studentId INT,  interviewerId INT,  grade INT NOT NULL);

--ALTER TABLE availability ADD CONSTRAINT AV_INT
--FOREIGN KEY(interviewerId) REFERENCES interviewer(interviewerId) ON DELETE SET DEFAULT;
--
--ALTER TABLE schedules ADD CONSTRAINT SC_INT
--FOREIGN KEY(interviewerId) REFERENCES interviewer(interviewerId) ON DELETE SET DEFAULT;
--
--ALTER TABLE schedules ADD CONSTRAINT SC_STD
--FOREIGN KEY(studentId) REFERENCES student(studentId) ON DELETE SET DEFAULT;
--
--ALTER TABLE interviews ADD CONSTRAINT INT_INT
--FOREIGN KEY(interviewerId) REFERENCES interviewer(interviewerId) ON DELETE SET DEFAULT;
--
--ALTER TABLE interviews ADD CONSTRAINT INT_STD
--FOREIGN KEY(studentId) REFERENCES student(studentId) ON DELETE SET DEFAULT;

INSERT INTO student (first_name, last_name) VALUES  ('Rohit', 'Chauhan'),  ('Shivam', 'Raj'),  ('Rohan', 'Dholakia');

INSERT INTO interviewer (first_name, last_name) VALUES  ('Bill', 'Gates'),  ('Elon', 'Musk'),  ('Satya', 'Kumar');

INSERT INTO availability (interviewerId, startTime, endTime) VALUES  (1,'2021-01-14T13:00:00', '2021-01-14T16:00:00'),  (2,'2021-01-15T07:00:00', '2021-01-15T11:00:00'),  (2,'2021-01-14T11:00:00', '2021-01-14T15:00:00'),  (3,'2021-01-21T13:00:00', '2021-01-21T16:00:00'),  (3,'2021-01-17T07:00:00', '2021-01-15T11:00:00'),  (1,'2021-01-14T11:00:00', '2021-01-14T15:00:00');