package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Admin_TeacherPanel extends JFrame implements View
{
   private JLabel nameTag;
   private JLabel cprTag;
   private JTextField name;
   private JTextField cpr;
   private JButton save;
   private JButton close;
   private Controller controller;
   
   public GUI_Admin_TeacherPanel() {
      super("Create teacher");
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      nameTag = new JLabel("Name: ");
      cprTag = new JLabel("CPR: ");
      name = new JTextField(20);
      cpr = new JTextField(10);
      save = new JButton("Save");
      close = new JButton("Close");
      
   }
   

   private void initializeComponents()
   {
      setSize(300,200);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }

   private void registerEventHandlers() {
      ButtonHandler handler = new ButtonHandler();
      save.addActionListener(handler);
      close.addActionListener(handler);
   }
   
   private void addComponentsToFrame()
   {
      JPanel mainPanel = new JPanel(new GridLayout(3,3));
      
      mainPanel.add(nameTag);
      mainPanel.add(name);
      mainPanel.add(cprTag);
      mainPanel.add(cpr);
      mainPanel.add(save);
      mainPanel.add(close);
      
      setContentPane(mainPanel);
      
   }
   
   public void start(Controller controller)
   {
      this.controller = controller;
      controller.setView(this);
      
   }

   public void show(String[] list)
   {
      name.setText(list[0]);
      cpr.setText(list[1]);
      
   }

   public String[] get()
   {
      String[] ret = new String[2];
      ret[0] = cpr.getText();
      ret[1] = name.getText();
      return ret;
   }
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == save) {
            if(cpr.getText().length() != 10) {
               JOptionPane.showMessageDialog(null ,"CPR is supposed to be 10 characters long", "CPR length error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  controller.execute(4);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            }
         }
         else if(e.getSource() == close) {
            dispose();
            try
            {
               controller.execute(5);
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
