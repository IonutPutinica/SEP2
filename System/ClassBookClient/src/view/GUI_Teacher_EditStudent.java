package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class GUI_Teacher_EditStudent extends JFrame implements View
{
   private JLabel nameTagStudent;
   private JLabel nameTagParent;
   private JTextField nameStudent;
   private JTextField nameParent;
   private JButton create;
   private JButton cancel;
   private Controller controller;
   private JLabel mathGrades;
   private JLabel mathAttendance;
   private JTextField mathG;
   private JTextField mathA;
   private JLabel litGrades;
   private JLabel litAttendance;
   private JTextField litG;
   private JTextField litA;
   private String cprStudent;
   private String cprParent;
   private String cprTeacher;

   public GUI_Teacher_EditStudent(String cprTeacher)
   {
      super("Edit Student");
      this.cprTeacher = cprTeacher;
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      nameTagStudent = new JLabel("Student Name: ");
      nameTagParent = new JLabel("Parent Name: ");
      nameStudent = new JTextField(20);
      nameParent = new JTextField(20);
      create = new JButton("Save");
      cancel = new JButton("Cancel");
      mathGrades = new JLabel("Math grades: ");
      mathG = new JTextField(20);
      mathAttendance = new JLabel("Math attendance: ");
      mathA = new JTextField(3);
      litGrades = new JLabel("Literature grades: ");
      litG = new JTextField(20);
      litAttendance = new JLabel("Literature attendance: ");
      litA = new JTextField(3);

   }

   private void initializeComponents()
   {
      setSize(450, 175);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

   }

   private void registerEventHandlers()
   {
      ButtonHandler handler = new ButtonHandler();
      create.addActionListener(handler);
      cancel.addActionListener(handler);
      
   }
   
   private void addComponentsToFrame()
   {
      JPanel mainPanel = new JPanel(new GridLayout(7, 2));

      mainPanel.add(nameTagStudent);
      mainPanel.add(nameStudent);
      mainPanel.add(nameTagParent);
      mainPanel.add(nameParent);
      mainPanel.add(mathGrades);
      mainPanel.add(mathG);
      mainPanel.add(mathAttendance);
      mainPanel.add(mathA);
      mainPanel.add(litGrades);
      mainPanel.add(litG);
      mainPanel.add(litAttendance);
      mainPanel.add(litA);
      mainPanel.add(create);
      mainPanel.add(cancel);

      setContentPane(mainPanel);

   }

   @Override
   public void start(Controller controller)
   {
      this.controller = controller;
      controller.setView(this);
      
   }

   @Override
   public void show(String[] list)
   {
      nameStudent.setText(list[1]);
      cprStudent = list[0];
      nameParent.setText(list[3]);
      cprParent = list[2];
      mathG.setText(list[4]);
      mathA.setText(list[5]);
      litG.setText(list[6]);
      litA.setText(list[7]);
      
   }

   @Override
   public String[] get()
   {
      String[] ret = new String[9];
      ret[0] = cprStudent;
      ret[1] = nameStudent.getText();
      ret[2] = cprParent;
      ret[3] = nameParent.getText();
      ret[4] = mathG.getText();
      ret[5] = mathA.getText();
      ret[6] = litG.getText();
      ret[7] = litA.getText();
      ret[8] = cprTeacher;
      return ret;
   }
   
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == create) {
            try
            {
               controller.execute(11);
            }
            catch (RemoteException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         
         if(e.getSource() == cancel) {
            try
            {
               controller.execute(13);
            }
            catch (RemoteException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      }
      
   }
}
