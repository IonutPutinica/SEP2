package domain.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable
{
   
   private String grades;
   private String attendance;
   private String type;
   private static final long serialVersionUID = 1L;

   
   public Subject(String type) {
      this.type = type;
      grades = new String();
   }
   
   public void setAttendance(String attendance) {
      this.attendance = attendance;
   }
   
   public String getAttendance() {
      return attendance;
   }
   
   public String getType() {
      return type;
   }
   
   public void setType(String type) {
      this.type = type;
   }
   
   public String getGrades() {
      return grades;
   }
   
   public void setGrades(String grades) {
      this.grades = grades;
   }
   
   public String toString() {
      return "Subject: " + type + " Grades: " + getGrades() + " Attendance: " + attendance;
   }
}
