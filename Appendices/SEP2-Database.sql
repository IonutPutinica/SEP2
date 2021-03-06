CREATE SCHEMA SchoolBook;

CREATE TABLE "Teacher"
(
    Name VARCHAR(50) NOT NULL,
    CPR VARCHAR(10) NOT NULL PRIMARY KEY
);

CREATE TABLE "Course"
(
    CourseID VARCHAR(3) NOT NULL PRIMARY KEY,
    TeacherCPR VARCHAR(10) NOT NULL,
    FOREIGN KEY (TeacherCPR) REFERENCES "Teacher"(CPR)
);

CREATE TABLE "Student"
(
    ClassID VARCHAR(3) NOT NULL,
    Name VARCHAR(50) NOT NULL,
    CPR VARCHAR(10) NOT NULL PRIMARY KEY,
    ParentCPR VARCHAR (10) NOT NULL,
    ParentName VARCHAR (50) NOT NULL,
    FOREIGN KEY (ClassID) REFERENCES "Course"(CourseID)
);

CREATE TABLE "Subject"
(
    SubjectType VARCHAR(50) NOT NULL PRIMARY KEY,
    StudentCPR VARCHAR NOT NULL,
    Grade VARCHAR(50) NOT NULL,
    Attendance VARCHAR(3),
    FOREIGN KEY (StudentCPR) REFERENCES "Student"(CPR)
);

CREATE TABLE "Message"
(
    ParentCPR VARCHAR(10) NOT NULL,
    TeacherCPR VARCHAR(10) NOT NULL,
    Time VARCHAR(16) NOT NULL,
    Message VARCHAR(280) NOT NULL,
    FOREIGN KEY (ParentCPR) REFERENCES "Student"(ParentCPR)
);
