package domain.model;

import java.io.Serializable;

public class Name implements IName, Serializable
{
   private String name;
   private static final long serialVersionUID = 1L;

   
   public Name(String name) {
      this.name = name;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
}
