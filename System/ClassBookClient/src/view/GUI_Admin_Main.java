package view;
import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Admin_Main extends JFrame implements View
{
   private JButton create;
   private JButton delete;
   private JButton open;
   private JLabel heading;
   private JList teacherList;
   JScrollPane listScroller;
   private Controller controller;
   
   
   public GUI_Admin_Main() {
      super("Administrator access");
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      create = new JButton("Create teacher");
      open = new JButton("View teacher");
      delete = new JButton("Delete teacher");
      heading = new JLabel("List of teachers: ");
      teacherList = new JList();
      teacherList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      teacherList.setLayoutOrientation(JList.VERTICAL);
      teacherList.setVisibleRowCount(-1);
      listScroller = new JScrollPane(teacherList);

   }
   
   private void initializeComponents()
   {
      setSize(500, 650);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      
   }
   
   private void registerEventHandlers() {
      ButtonHandler handler = new ButtonHandler();
      create.addActionListener(handler);
      delete.addActionListener(handler);
      open.addActionListener(handler);
   }

   private void addComponentsToFrame()
   {
      JPanel mainFrame = new JPanel(new BorderLayout());
      JPanel buttons = new JPanel(new GridLayout(3,1));
      
      buttons.add(create);
      buttons.add(open);
      buttons.add(delete);
      
      mainFrame.add(heading, BorderLayout.NORTH);
      mainFrame.add(listScroller, BorderLayout.CENTER);
      mainFrame.add(buttons, BorderLayout.EAST);
      
      setContentPane(mainFrame);
      
   }
   
   
   public void start(Controller controller)
   {
      this.controller = controller;
      controller.setView(this);
      
   }

   
   public void show(String[] list)
   {
      teacherList.setListData(list);      
   }

   
   public String[] get()
   {
      String[] ret = new String[1];
      ret[0] = teacherList.getSelectedValue() + "";
      return ret;
   }

   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource().equals(create)) {
            try
            {
               controller.execute(1);
            }
            catch (RemoteException e1)
            {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         else if(e.getSource().equals(open)) {
            if(teacherList.getSelectedValue() == null) {
               JOptionPane.showMessageDialog(null ,"Please select a teacher from the list", "Selection error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  controller.execute(2);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
            } 
         }
         else if(e.getSource().equals(delete)) {
            if(teacherList.getSelectedValue() == null) {
               JOptionPane.showMessageDialog(null ,"Please select a teacher from the list", "Selection error", JOptionPane.ERROR_MESSAGE);
            }
            else {
               try
               {
                  controller.execute(3);
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
