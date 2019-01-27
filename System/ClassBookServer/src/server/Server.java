package server;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

import database.DatabaseAdapter;
import domain.mediator.ClientInterface;
import domain.mediator.ServerInterface;
import domain.model.Course;
import domain.model.School;
import domain.model.Student;
import domain.model.Teacher;

public class Server extends UnicastRemoteObject implements ServerInterface
{
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private static Server instance;
   private School school;
   private DatabaseAdapter dbs;
   
   public Server() throws RemoteException, MalformedURLException, ClassNotFoundException, SQLException {
      super();
      startRegistry();
      Naming.rebind("ClassBook", this);
      System.out.println("Starting server...");
      dbs = new DatabaseAdapter();
      school = dbs.loadSchool();
      System.out.println("Database load completed.");
      
   }
   
   private void startRegistry() throws RemoteException{
      try {
         Registry reg = LocateRegistry.createRegistry(1099);
         System.out.println("Starting registry...");
      }

      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   public static Server getInstance() throws RemoteException, MalformedURLException, ClassNotFoundException, SQLException {
      if(instance == null)
         instance = new Server();
      return instance;
   }

   
   public void addTeacher(Teacher teacher) throws RemoteException
   {
      school.getTeacherList().addTeacher(teacher);
      
   }

   
   public void editTeacher(Teacher teacher) throws RemoteException
   {
      for(int i = 0; i < school.getTeacherList().size(); i++) {
         if(school.getTeacherList().getTeacher(i).getCPR().equals(teacher.getCPR())) {
            school.getTeacherList().getTeacher(i).setName(teacher.getName());
         }
      }
      for(int n = 0; n < school.getCourseListSize();n++) {
         if(school.getCourse(n).getTeacher().getCPR().equals(teacher.getCPR())) {
            school.getCourse(n).setTeacher(teacher);
         }
      }
      
   }

   
   public void deleteTeacher(Teacher teacher) throws RemoteException
   {
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(teacher.getCPR())) {
            school.removeCourse(i);
         }
      }
      for(int n = 0; n < school.getTeacherList().size();n++) {
         if(school.getTeacherList().getTeacher(n).getCPR().equals(teacher.getCPR())) {
            school.getTeacherList().removeTeacher(n);
         }
      }
      
   }

   
   public void createCourse(Course course) throws RemoteException
   {
      school.addCourse(course);      
   }

   
   public void addStudent(Student student, String cprTeacher) throws RemoteException
   {
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(cprTeacher)) {
            school.getCourse(i).addStudent(student);
         }
      }
      
   }

  
   public void editStudent(Student student, String cprTeacher) throws RemoteException
   {
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(cprTeacher)) {
            school.getCourse(i).setStudent(student);
         }
      }
      
   }

   @Override
   public void deleteStudent(String cprStudent, String cprTeacher) throws RemoteException
   {
      for(int i = 0; i < school.getCourseListSize();i++) {
         if(school.getCourse(i).getTeacher().getCPR().equals(cprTeacher)) {
            school.getCourse(i).removeStudent(cprStudent);
         }
      }
      
   }

   
   public School getSchool() throws RemoteException{
      return school;
   }
   
}
