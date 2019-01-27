package server;

import java.rmi.RemoteException;

import domain.mediator.ServerInterface;

public class ShutdownInitiator extends Thread
{
   private Shutdown shutdown;
   private ServerInterface server;
   
   public ShutdownInitiator(ServerInterface server) throws ClassNotFoundException {
      shutdown = new Shutdown();
   }
   public void run() {
    try
   {
      shutdown.setSchool(server.getSchool());
      shutdown.run();
   }
   catch (RemoteException e)
   {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }  
   }
}
