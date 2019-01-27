package domain.mediator;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import domain.model.Course;
import domain.model.School;
import domain.model.Student;
import domain.model.Teacher;

public interface ClientInterface extends Remote
{
   public void addTeacher(Teacher teacher) throws RemoteException;
   public void editTeacher(Teacher teacher) throws RemoteException;
   public void deleteTeacher(Teacher teacher) throws RemoteException;
   public void addStudent(Student student, String cprTeacher) throws RemoteException;
   public void editStudent(Student student, String cprTeacher) throws RemoteException;
   public void deleteStudent(String cprStudent, String cprTeacher) throws RemoteException;
   public void createCourse(Course course) throws RemoteException;
}
