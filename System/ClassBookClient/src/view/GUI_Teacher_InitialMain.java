package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Teacher_InitialMain extends JFrame implements View
{

   private JButton createClass;
   private JTextField className;
   private JLabel nameTag;
   private Controller controller;
   private String cpr;

   public GUI_Teacher_InitialMain(String cpr)
   {
      super("Teacher");
      this.cpr = cpr;
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      createClass = new JButton("Create Class");
      nameTag = new JLabel("Name: ");
      className = new JTextField(10);

   }

   private void initializeComponents()
   {
      setSize(250, 150);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   private void registerEventHandlers()
   {
      ButtonHandler handler = new ButtonHandler();
      createClass.addActionListener(handler);
      
   }
   
   private void addComponentsToFrame()
   {
      JPanel buttons = new JPanel(new GridLayout(3, 1));

      buttons.add(nameTag);
      buttons.add(className);
      buttons.add(createClass);
      setContentPane(buttons);
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
      this.cpr = list[0];
      
   }

   @Override
   public String[] get()
   {
      String[] ret = new String[2];
      ret[0] = cpr;
      ret[1] = className.getText();
      return ret;
   }
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(className.getText().length() == 0) {
            JOptionPane.showMessageDialog(null ,"Please write a name for the Class", "Name error", JOptionPane.ERROR_MESSAGE);
         }
         else {
            try
            {
               controller.execute(7);
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
