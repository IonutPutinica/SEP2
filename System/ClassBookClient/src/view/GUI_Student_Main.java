package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;

public class GUI_Student_Main extends JFrame implements View
{

   private JList subjectList;

   public GUI_Student_Main()
   {
      super("Student access");
      createComponents();
      initializeComponents();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      subjectList = new JList();
   }

   private void initializeComponents()
   {
      setSize(400, 100);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

   }

   private void addComponentsToFrame()
   {

      JPanel mainPanel = new JPanel(new GridLayout(1, 1));

      mainPanel.add(subjectList);

      setContentPane(mainPanel);

   }

   @Override
   public void start(Controller controller)
   {
      controller.setView(this);
      
   }

   @Override
   public void show(String[] list)
   {
      subjectList.setListData(list);
      
   }

   @Override
   public String[] get()
   {
      String[] af = new String[1];
      return af;
   }

}