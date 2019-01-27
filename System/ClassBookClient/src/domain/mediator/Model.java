package domain.mediator;

import java.rmi.RemoteException;

import domain.model.Parent;
import domain.model.Student;
import domain.model.Teacher;
import domain.model.TeacherList;

public interface Model
{
   public String determineLogin(String entry);
   public Teacher extractTeacherData(String entry);
   public Student extractParentData(String entry);
   public Student extractStudentData(String entry);
   public String[] extractAdminData();
   public void deleteTeacher(String cpr)  throws RemoteException;
   public void createTeacher(String name, String cpr)  throws RemoteException;
   public void editTeacher(String name, String cpr)  throws RemoteException;
   public String[] getStudentList(String cpr);
   public void createCourse(String name, String cpr) throws RemoteException;
   public void createStudent(String[] student) throws RemoteException;
   public void editStudent(String[] student) throws RemoteException;
   public String[] getStudent(String cpr);
   public void deleteStudent(String cprStudent, String cprTeacher) throws RemoteException;
   public String[] extractStudentSubjects(String studentCPR);
   
}
