package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class GUI_Admin_EditTeacherPanel extends JFrame implements View
{
   private JLabel nameTag;
   private JTextField name;
   private JButton save;
   private JButton close;
   private Controller controller;
   private String cpr;
   
   public GUI_Admin_EditTeacherPanel() {
      super("Edit teacher");
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      nameTag = new JLabel("Name: ");
      name = new JTextField(20);
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
      JPanel mainPanel = new JPanel(new GridLayout(2,2));
      
      mainPanel.add(nameTag);
      mainPanel.add(name);
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
      cpr = list[1];
      
   }

   public String[] get()
   {
      String[] ret = new String[2];
      ret[0] = cpr;
      ret[1] = name.getText();
      return ret;
   }
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == save) {
               try
               {
                  controller.execute(6);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
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
