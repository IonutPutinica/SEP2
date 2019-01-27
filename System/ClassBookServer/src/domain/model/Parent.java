package domain.model;

import java.io.Serializable;

public class Parent extends Person implements Serializable
{
   private static final long serialVersionUID = 1L;

   
   public Parent(String name, String cpr) {
      super(name, cpr);
   }
   
}
