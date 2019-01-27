package domain.model;

import java.io.Serializable;

public class Teacher extends Person implements Serializable
{
   private static final long serialVersionUID = 1L;

   
   public Teacher(String name, String cpr) {
      super(name, cpr);
   }
}
