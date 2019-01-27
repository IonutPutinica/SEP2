package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;

public class GUI_Parent_Main extends JFrame implements View
{
   private JButton viewContact;
   private JList subjectList;

   public GUI_Parent_Main()
   {
      super("Parent access");
      createComponents();
      initializeComponents();
      addComponentsToFrame();
   }

   private void createComponents()
   {
      viewContact = new JButton("View messages/Contact teacher");
      subjectList = new JList();
   }

   private void initializeComponents()
   {
      setSize(400, 200);
      setVisible(true);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

   }

   private void addComponentsToFrame()
   {
      JPanel mainFrame = new JPanel(new BorderLayout());
      JPanel buttons = new JPanel(new GridLayout(1, 1));

      buttons.add(viewContact);

      mainFrame.add(subjectList, BorderLayout.CENTER);
      mainFrame.add(buttons, BorderLayout.SOUTH);

      setContentPane(mainFrame);

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
