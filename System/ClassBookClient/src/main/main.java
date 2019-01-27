package main;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import controller.Controller;
import domain.mediator.ConcreteModel;
import domain.mediator.Model;
import view.GUI_Login;
import view.View;

public class main
{

   public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException
   {
      Model model = new ConcreteModel();
      View view = new GUI_Login();
      Controller controller = new Controller(model, view);
      view.start(controller);

   }

}
