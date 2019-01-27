package domain.mediator;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import domain.model.Course;
import domain.model.Parent;
import domain.model.ProxyStudent;
import domain.model.School;
import domain.model.Student;
import domain.model.Subject;
import domain.model.Teacher;
import domain.model.TeacherList;

public class ConcreteModel implements Model, Serializable
{
   private static final long serialVersionUID = 1L;
   private School school;
   private Client client;
   
   public ConcreteModel() throws RemoteException, MalformedURLException, NotBoundException {
      client = new Client();
      school = client.getSchool();
   }
   
   public String determineLogin(String entry)
   {
      int index = 0;
            for(int i = 0; i< school.getCourseListSize(); i++) {
               for(int x = 0; x < school.getCourse(i).getStudentListSize(); x++) {
                  if(school.getCourse(i).getStudent(x).getParent().getCPR().equals(entry)) {
                     index = 3;
                  }
               }
            }
            for(int i = 0; i< school.getCourseListSize(); i++) {
               for(int x = 0; x < school.getCourse(i).getStudentListSize(); x++) {
                  if(school.getCourse(i).getStudent(x).getCPR().equals(entry)) {
                     index = 2;
                  }
               }
            }
         
            for(int i = 0; i < school.getTeacherList().size();i++) {
               if(school.getTeacherList().getTeacher(i).getCPR().equals(entry)) {
                  index = 1;
            }
         }
      
      switch(index) {
         case 0: return "User not found";
         case 1: return "Teacher";
         case 2: return "Student";
         case 3: return "Parent";
      }
      return "";
   }

   @Override
   public Teacher extractTeacherData(String entry)
   {
      int index = 0;
      for(int i = 0; i < school.getTeacherList().size();i++) {
         if(school.getTeacherList().getTeacher(i).getCPR().equals(entry)) {
            index = i;
         }
      }
      
      return school.getTeacherList().getTeacher(index);
   }

   @Override
   public Student extractParentData(String entry)
   {
      int index1 = 0;
      int index2 = 0;
      for(int i = 0; i< school.getCourseListSize(); i++) {
         for(int x = 0; x < school.getCourse(i).getStudentListSize(); x++) {
            if(school.getCourse(i).getStudent(x).getParent().getCPR().equals(entry)) {
               index1 = i;
               index2 = x;
            }
         }
      }
      return school.getCourse(index1).getStudent(index2);
   }

   @Override
   public Student extractStudentData(String entry)
   {
      int index1 = 0;
      int index2 = 0;
      for(int i = 0; i< school.getCourseListSize(); i++) {
         for(int x = 0; x < school.getCourse(i).getStudentListSize(); x++) {
            if(school.getCourse(i).getStudent(x).getCPR().equals(entry)) {
               index1 = i;
               index2 = x;
            }
         }
      }
      return school.getCourse(index1).getStudent(index2);
   }

   @Override
   public String[] extractAdminData()
   {
      return school.getTeacherList().listTeachers();
   }
   
   public void deleteTeacher(String cpr) throws RemoteException {
      for(int i = 0; i < school.getTeacherList().size();i++) {
         if(school.getTeacherList().getTeacher(i).getCPR().equals(cpr)) {
            client.deleteTeacher(school.getTeacherList().getTeacher(i));
            school.getTeacherList().removeTeacher(i);
         }
      }
   }

   @Override
   public void createTeacher(String name, String cpr) throws RemoteException
   {
      Teacher teacher = new Teacher(name, cpr);
      school.getTeacherList().addTeacher(teacher);
      client.addTeacher(teacher);
      
   }

   @Override
   public void editTeacher(String name, String cpr) throws RemoteException
   {
      for(int i = 0; i < school.getTeacherList().size();i++) {
         if(school.getTeacherList().getTeacher(i).getCPR().equals(cpr)) {
            school.getTeacherList().getTeacher(i).setName(name);
            client.editTeacher(school.getTeacherList().getTeacher(i));
         }
      }
      
   }

   @Override
   public String[] getStudentList(String cpr)
   {
      int index = 0;
      short x = 0;
      for(int i = 0; i < school.getCourseListSize(); i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(cpr)) {
            x = 1;
            index = i;
         }
      }
      if(x == 1) {
         return school.getCourse(index).listOfStudents(); 
      }
      else {
         return null;
      }
      
   }

   @Override
   public void createCourse(String name, String cpr) throws RemoteException
   {
      int index = 0;
      for(int i = 0; i < school.getTeacherList().size();i++) {
         if(school.getTeacherList().getTeacher(i).getCPR().equals(cpr)) {
            index = i;
         }
      }
      Course course = new Course(name, school.getTeacherList().getTeacher(index));
      client.createCourse(course);
      school.addCourse(course);
   }

   @Override
   public void createStudent(String[] student) throws RemoteException
   {
      Parent parent = new Parent(student[3], student[2]);
      Student stu = new ProxyStudent(student[1], student[0], parent);
      Subject math = new Subject("Math");
      math.setGrades(student[4]);
      math.setAttendance(student[5]);
      Subject literature = new Subject("Literature");
      literature.setGrades(student[6]);
      literature.setAttendance(student[7]);
      stu.setMath(math);
      stu.setLiterature(literature);
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(student[8])) {
            school.getCourse(i).addStudent(stu);
         }
      }
      client.addStudent(stu, student[8]);
      
   }

   @Override
   public void editStudent(String[] student) throws RemoteException
   {
      Parent parent = new Parent(student[3], student[2]);
      Student stu = new ProxyStudent(student[1], student[0], parent);
      Subject math = new Subject("Math");
      math.setGrades(student[4]);
      math.setAttendance(student[5]);
      Subject literature = new Subject("Literature");
      literature.setGrades(student[6]);
      literature.setAttendance(student[7]);
      stu.setMath(math);
      stu.setLiterature(literature);
      for(int i = 0; i < school.getCourseListSize(); i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(student[8])) {
            school.getCourse(i).setStudent(stu);
         }
      }
      client.editStudent(stu, student[8]);
      
   }

   @Override
   public String[] getStudent(String cpr)
   {
      String[] stu = new String[8];
      for(int i = 0; i < school.getCourseListSize();i++) {
         for(int n = 0; n < school.getCourse(i).getStudentListSize();n++) {
            stu[0] = school.getCourse(i).getStudent(n).getCPR();
            stu[1] = school.getCourse(i).getStudent(n).getName();
            stu[2] = school.getCourse(i).getStudent(n).getParent().getCPR();
            stu[3] = school.getCourse(i).getStudent(n).getParent().getName();
            stu[4] = school.getCourse(i).getStudent(n).getMath().getGrades();
            stu[5] = school.getCourse(i).getStudent(n).getMath().getAttendance();
            stu[6] = school.getCourse(i).getStudent(n).getLiterature().getGrades();
            stu[7] = school.getCourse(i).getStudent(n).getLiterature().getAttendance();
          }
      }
      return stu;
   }

   @Override
   public void deleteStudent(String cprStudent, String cprTeacher) throws RemoteException
   {
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(cprTeacher)) {
            school.getCourse(i).removeStudent(cprStudent);
            client.deleteStudent(cprStudent, cprTeacher);
         }
      }
   }

   @Override
   public String[] extractStudentSubjects(String studentCPR)
   {
      int index1 = 0;
      int index2 = 0;
      for(int i = 0; i< school.getCourseListSize(); i++) {
         for(int x = 0; x < school.getCourse(i).getStudentListSize(); x++) {
            if(school.getCourse(i).getStudent(x).getCPR().equals(studentCPR)) {
               index1 = i;
               index2 = x;
            }
         }
      }
      String[] subjectorinos = new String[2];
      subjectorinos[0] = school.getCourse(index1).getStudent(index2).getMath().toString();
      subjectorinos[1] = school.getCourse(index1).getStudent(index2).getLiterature().toString();
      return subjectorinos;
   }
}
