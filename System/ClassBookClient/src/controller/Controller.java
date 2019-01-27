package controller;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import domain.mediator.Model;
import view.*;

public class Controller implements Serializable
{
   private Model model;
   private View view;
   private static final long serialVersionUID = 1L;
   
   public Controller(Model model, View view) {
      this.model = model;
      this.view = view;
   }
   
   public void setView(View view) {
      this.view = view;
   }
   
   public void execute(int choice) throws RemoteException {
      switch(choice) {
         //LOG IN AUTHENTICATION
         case 0:  if(model.determineLogin(view.get()[0]).equals("Teacher")) {
                      String cpr = new String(model.extractTeacherData(view.get()[0]).getCPR());
                      if(model.getStudentList(cpr) == null) {
                         view.dispose();
                         GUI_Teacher_InitialMain tmain = new GUI_Teacher_InitialMain(cpr);
                         tmain.start(this);
                         String[] cperino = new String[1];
                         cperino[0] = cpr;
                         tmain.show(cperino);
                      }
                      else {
                         view.dispose();
                         GUI_Teacher_Main mainteach = new GUI_Teacher_Main(cpr);
                         mainteach.start(this);
                         mainteach.show(model.getStudentList(cpr));
                      }
                     
                  }
                  
                  else if(model.determineLogin(view.get()[0]).equals("Student")) {  
                        String[] subjectss = model.extractStudentSubjects(view.get()[0]);
                        view.dispose();
                        GUI_Student_Main stuM = new GUI_Student_Main();
                        stuM.start(this);
                        stuM.show(subjectss); 
                     
                  }
                  
                  else if(model.determineLogin(view.get()[0]).equals("Parent")) {
                   
                     String[] subjectss = model.extractStudentSubjects(view.get()[0]);
                     view.dispose();
                     GUI_Parent_Main parM = new GUI_Parent_Main();
                     parM.start(this);
                     parM.show(subjectss); 
                     
                  }
                  
                  else if(view.get()[0].equals("BigBrother")) {    
                     if(model.extractAdminData().length == 0) {
                        view.dispose();
                        GUI_Admin_Main admin = new GUI_Admin_Main();
                        admin.start(this);
                     }
                     else{
                        view.dispose();
                        GUI_Admin_Main admin = new GUI_Admin_Main();
                        admin.start(this);
                        admin.show(model.extractAdminData());   
                     }
                  }
                  //returns error if login is invalid
                  else {
                     JOptionPane.showMessageDialog(null ,"Account doesn not exist or CPR is wrong. Make sure "
                           + "that CPR is written correctly. (Ex. 0000000000)", "Login error", JOptionPane.ERROR_MESSAGE);
                  }
                  break;
         //CREATE TEACHER WINDOW
         case 1:  view.dispose();
                  GUI_Admin_TeacherPanel createteacher = new GUI_Admin_TeacherPanel();
                  createteacher.start(this);
                  break;
         //EDIT TEACHER WINDOW
         case 2:     String[] teacher = new String[2];
                     teacher[0] = model.extractTeacherData(view.get()[0].substring(0, 10)).getName();
                     teacher[1] = model.extractTeacherData(view.get()[0].substring(0, 10)).getCPR();
                     view.dispose();
                     GUI_Admin_EditTeacherPanel editteacher = new GUI_Admin_EditTeacherPanel();
                     editteacher.start(this);
                     editteacher.show(teacher);
                  break;
         //DELETE TEACHER
         case 3:  model.deleteTeacher(view.get()[0].substring(0, 10));
                  view.show(model.extractAdminData());
                  break;
         //CREATE TEACHER
         case 4:  
                  String addTeacher1 = new String(view.get()[1]);
                  String addTeacher2 = new String(view.get()[0]);
                  model.createTeacher(addTeacher1,addTeacher2);
                  view.dispose();
                  GUI_Admin_Main admin = new GUI_Admin_Main();
                  admin.start(this);
                  view.show(model.extractAdminData());
                  break;
         //OPENS ADMIN MAIN (AFTER CREATE/EDIT WINDOW IS CLOSED)
         case 5:  view.dispose();
                  GUI_Admin_Main adminMain = new GUI_Admin_Main();
                  adminMain.start(this);
                  adminMain.show(model.extractAdminData());
                  break;
         //EDIT TEACHER NAME
         case 6:  String editTeacher1 = new String(view.get()[1]);
                  String editTeacher2 = new String(view.get()[0]);
                  model.editTeacher(editTeacher1, editTeacher2);
                  view.dispose();
                  GUI_Admin_Main admin1 = new GUI_Admin_Main();
                  admin1.start(this);
                  view.show(model.extractAdminData());
                  break;
         //CREATE COURSE/ TEACHER MAIN WINDOW
         case 7:  model.createCourse(view.get()[0], view.get()[1]);
                  String cpr = new String(view.get()[0]);
                  view.dispose();
                  GUI_Teacher_Main teachMain = new GUI_Teacher_Main(cpr);
                  teachMain.start(this);
                  teachMain.show(model.getStudentList(cpr));
                  break;
         //CREATE STUDENT WINDOW
         case 8: String cpr1 = new String(view.get()[1]);
                 view.dispose();
                 GUI_Teacher_AddStudent createStudent = new GUI_Teacher_AddStudent(cpr1);
                 createStudent.start(this);
                 break;
         //CREATE STUDENT
         case 9: model.createStudent(view.get());
                 String cpr2 = new String(view.get()[8]);
                 view.dispose();
                 GUI_Teacher_Main mainTech = new GUI_Teacher_Main(cpr2);
                 mainTech.start(this);
                 mainTech.show(model.getStudentList(cpr2));
                 break;
         //EDIT STUDENT WINDOW
         case 10: String cprStudento = new String(view.get()[0]);
                  String cprTeachero = new String(view.get()[1]);
                  view.dispose();
                  GUI_Teacher_EditStudent editStudent = new GUI_Teacher_EditStudent(cprTeachero);
                  editStudent.start(this);
                  editStudent.show(model.getStudent(cprStudento));
                  break;
         //EDIT STUDENT
         case 11: model.editStudent(view.get());
                  String cpr3 = new String(view.get()[8]);
                  view.dispose();
                  GUI_Teacher_Main mainTecher = new GUI_Teacher_Main(cpr3);
                  mainTecher.start(this);;
                  mainTecher.show(model.getStudentList(cpr3));
                  break;
         //DELETE STUDENT
         case 12:  model.deleteStudent(view.get()[0], view.get()[1]);
                   view.show(model.getStudentList(view.get()[1]));
                   break;
         //OPENS Teacher MAIN (AFTER CREATE/EDIT WINDOW IS CLOSED)
         case 13:    String cprT = new String(view.get()[8]);
                     view.dispose();
                     GUI_Teacher_Main techyMain = new GUI_Teacher_Main(cprT);
                     techyMain.start(this);
                     techyMain.show(model.getStudentList(cprT));
                     break;
      }
   }
}
