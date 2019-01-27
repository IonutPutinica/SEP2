package server;

import java.sql.SQLException;

import database.DatabaseAdapter;
import domain.model.School;

public class Shutdown extends Thread
{
   private School school;
   private DatabaseAdapter dbs;
   
   public Shutdown() throws ClassNotFoundException {
      dbs = new DatabaseAdapter();
   }
   
   public void setSchool(School school) {
      this.school = school;
   }
   
   public void run() {
        try
      {
         dbs.saveSchool(school);
      }
      catch (SQLException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
        
   }
}
