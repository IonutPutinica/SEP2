package view;
import javax.swing.*;
import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GUI_Login extends JFrame implements View
{
   private Controller controller;
   private JButton login;
   private JTextField entry;
   private JLabel heading;
   
   public GUI_Login() {
      super("Class Book");
      createComponents();
      initializeComponents();
      registerEventHandlers();
      addComponentsToFrame();
   }
   
   private void createComponents() {
      login = new JButton("Log in");
      entry = new JTextField(10);
      heading = new JLabel("Enter CPR", JLabel.CENTER);
   }
   
   private void initializeComponents() {
      setSize(300,150);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   
   private void registerEventHandlers() {
      ButtonHandler handler = new ButtonHandler();
      login.addActionListener(handler);
   }
   
   private void addComponentsToFrame() {
      JPanel contentPane = new JPanel(new GridLayout(3,1));
      
      contentPane.add(heading);
      contentPane.add(entry);
      contentPane.add(login);
      
      setContentPane(contentPane);
      
   }
   
   public void start(Controller controller)
   {
      this.controller = controller;
      
   }
   
   public String[] get() {
      String[] ret = new String[1];
      ret[0] = entry.getText();
      return ret;
   }
   
   
   private class ButtonHandler implements ActionListener{

      public void actionPerformed(ActionEvent e)
      {
         if(e.getSource() == login) {
            
            if(entry.getText().length() == 10) {
               try
               {
                  controller.execute(0);
               }
               catch (RemoteException e1)
               {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
 
            }
            
            else {
               
               JOptionPane.showMessageDialog(null ,"CPR is supposed to be 10 characters long", "CPR length error", JOptionPane.ERROR_MESSAGE);
            
            }
         }
         
      }
      
   }


   @Override
   public void show(String[] list)
   {
      // TODO Auto-generated method stub
      
   }
}
