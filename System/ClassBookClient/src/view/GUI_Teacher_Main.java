package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Teacher_Main extends JFrame implements View
{
   private JButton viewContact;
   private JButton create;
   private JButton delete;
   private JButton open;
   private JLabel heading;
   private JList studentsList;
   private Controller controller;
   private String cpr;
   private String[] ret;

   public GUI_Teacher_Main(String cpr)
   {
      super("Teacher");
      this.cpr = cpr;
      ret = new String[2];
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      viewContact = new JButton("View messages/Contact parent");
      create = new JButton("Create student");
      open = new JButton("View Student");
      delete = new JButton("Delete students");
      heading = new JLabel("List of students: ");
      studentsList = new JList();
   }

   private void initializeComponents()
   {
      setSize(500, 650);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

   }


   private void registerEventHandlers()
   {
      ButtonHandler handler = new ButtonHandler();
      create.addActionListener(handler);
      open.addActionListener(handler);
      delete.addActionListener(handler);
      
   }
   
   private void addComponentsToFrame()
   {
      JPanel mainFrame = new JPanel(new BorderLayout());
      JPanel buttons = new JPanel(new GridLayout(4, 1));

      buttons.add(create);
      buttons.add(open);
      buttons.add(delete);
      buttons.add(viewContact);
      mainFrame.add(heading, BorderLayout.NORTH);
      mainFrame.add(studentsList, BorderLayout.CENTER);
      mainFrame.add(buttons, BorderLayout.EAST);

      setContentPane(mainFrame);

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
      studentsList.setListData(list);
      
   }

   @Override
   public String[] get()
   {
      
      ret[1] = cpr;
      return ret;
   }
   
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource().equals(create)) {
            try
            {
               ret[0] = "";
               controller.execute(8);
            }
            catch (RemoteException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource().equals(open)) {
            if(studentsList.getSelectedValue() == null) {
               JOptionPane.showMessageDialog(null ,"Please select a student from the list", "Selection error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  ret[0] = studentsList.getSelectedValue().toString().substring(0,10);
                  controller.execute(10);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            } 
         }
         else if(e.getSource().equals(delete)) {
            if(studentsList.getSelectedValue() == null) {
               JOptionPane.showMessageDialog(null ,"Please select a student from the list", "Selection error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  ret[0] = studentsList.getSelectedValue().toString().substring(0,10);
                  controller.execute(12);
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
}
