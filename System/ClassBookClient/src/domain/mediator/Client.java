package domain.mediator;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import domain.model.Course;
import domain.model.School;
import domain.model.Student;
import domain.model.Teacher;


public class Client extends UnicastRemoteObject implements ClientInterface
{
   private static final long serialVersionUID = 1L;
   private ServerInterface server;
   private School school;
   
   public Client() throws RemoteException, MalformedURLException, NotBoundException {
      super();
      server = (ServerInterface) Naming.lookup("rmi://localhost/ClassBook");
   }


   public School getSchool() throws RemoteException
   {
      
      return server.getSchool();
   }
   
   
   public void addTeacher(Teacher teacher) throws RemoteException
   {
      server.addTeacher(teacher);
      
   }

   
   public void editTeacher(Teacher teacher) throws RemoteException
   {
      server.editTeacher(teacher);
      
   }

   
   public void deleteTeacher(Teacher teacher) throws RemoteException
   {
      server.deleteTeacher(teacher);
      
   }

   
   public void addStudent(Student student, String cprTeacher)
         throws RemoteException
   {
      server.addStudent(student, cprTeacher);
      
   }

   
   public void editStudent(Student student, String cprTeacher)
         throws RemoteException
   {
      server.editStudent(student, cprTeacher);
      
   }

   
   public void deleteStudent(String cprStudent, String cprTeacher)
         throws RemoteException
   {
      server.deleteStudent(cprStudent, cprTeacher);
      
   }

   
   public void createCourse(Course course) throws RemoteException
   {
      server.createCourse(course);
      
   }

}
