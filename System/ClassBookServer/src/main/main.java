package main;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import server.Server;
import server.Shutdown;
import server.ShutdownInitiator;

public class main
{

   public static void main(String[] args) throws RemoteException, MalformedURLException, ClassNotFoundException, SQLException
   {
      Server server = new Server();
      ShutdownInitiator shutdown = new ShutdownInitiator(server);
      Runtime.getRuntime().addShutdownHook(shutdown);
      System.out.println("Database load completed");
   }

}
