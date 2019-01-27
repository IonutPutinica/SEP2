package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Teacher_AddStudent extends JFrame implements View
{
   private JLabel nameTagStudent;
   private JLabel cprTagStudent;
   private JLabel nameTagParent;
   private JLabel cprTagParent;
   private JTextField nameStudent;
   private JTextField cprStudent;
   private JTextField nameParent;
   private JTextField cprParent;
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
   private String cpr;

   public GUI_Teacher_AddStudent(String cpr)
   {
      super("Add Student");
      this.cpr = cpr;
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      nameTagStudent = new JLabel("Student Name: ");
      cprTagStudent = new JLabel("Student CPR: ");
      nameTagParent = new JLabel("Parent Name: ");
      cprTagParent = new JLabel("Parent CPR: ");
      nameStudent = new JTextField(20);
      cprStudent = new JTextField(10);
      nameParent = new JTextField(20);
      cprParent = new JTextField(10);
      create = new JButton("Create");
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
      JPanel mainPanel = new JPanel(new GridLayout(9, 2));

      mainPanel.add(nameTagStudent);
      mainPanel.add(nameStudent);
      mainPanel.add(nameTagParent);
      mainPanel.add(nameParent);
      mainPanel.add(cprTagStudent);
      mainPanel.add(cprStudent);
      mainPanel.add(cprTagStudent);
      mainPanel.add(cprStudent);
      mainPanel.add(cprTagParent);
      mainPanel.add(cprParent);
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
      
      
   }

   @Override
   public String[] get()
   {
      String[] ret = new String[9];
      ret[0] = cprStudent.getText();
      ret[1] = nameStudent.getText();
      ret[2] = cprParent.getText();
      ret[3] = nameParent.getText();
      ret[4] = mathG.getText();
      ret[5] = mathA.getText();
      ret[6] = litG.getText();
      ret[7] = litA.getText();
      ret[8] = cpr;
      return ret;
   }
   
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == create) {
            if(cprStudent.getText().length() != 10) {
               JOptionPane.showMessageDialog(null ,"Student CPR is supposed to be 10 characters long", "CPR length error", JOptionPane.ERROR_MESSAGE);
            }
            else if(cprParent.getText().length() != 10) {
               JOptionPane.showMessageDialog(null ,"Parent CPR is supposed to be 10 characters long", "CPR length error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  controller.execute(9);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
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
