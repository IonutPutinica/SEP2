package domain.model;
import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable
{
   private String name;
   private ArrayList<Student> students;
   private Teacher teacher;
   private static final long serialVersionUID = 1L;

   
   public Course(String name, Teacher teacher) {
      this.name = name;
      students = new ArrayList<Student>();
      this.teacher = teacher;
   }
   
   public void addStudent(Student student) {
      students.add(student);
      student.setCourse(this);
   }
   
   public ProxyStudent getStudent(int index) {
      return (ProxyStudent)students.get(index);
   }
   
   public void removeStudent(String cpr) {
      for(int i = 0 ; i < students.size(); i++) {
         if(students.get(i).getCPR().equals(cpr)) {
            students.remove(i);
         }
      }
   }
   
   public int getStudentListSize() {
      return students.size();
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public Teacher getTeacher() {
      return teacher;
   }
   
   public String[] listOfStudents() {
      String[] listStudents = new String[students.size()];
      for(int i = 0 ; i < students.size(); i++) {
         listStudents[i] = students.get(i).toString();
      }
      return listStudents;
   }
   
   public void setTeacher(Teacher teacher) {
      this.teacher = teacher;
   }
   
   public void setStudent(Student student) {
      int index = 0;
      for(int i = 0; i < students.size(); i++) {
         if(students.get(i).getCPR().equals(student.getCPR())) {
            index = i;
         }
      }
      students.set(index, student);
   }
   
}  
