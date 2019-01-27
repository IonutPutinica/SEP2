package domain.model;

import java.io.Serializable;

public class Person implements Serializable
{
   private IName name;
   private ICPR cpr;
   private static final long serialVersionUID = 1L;

   
   public Person(String name, String cpr) {
      this.name = new Name(name);
      this.cpr = new CPR(cpr);
   }
   
   public String getName() {
    return name.getName();  
   }
   
   public void setName(String value) {
      name.setName(value);
   }
   
   public String getCPR() {
      return cpr.getCPR();
   }
   
   public void setCPR(String value) {
      cpr.setCPR(value);
   }
   
   public String toString() {
      return cpr.getCPR() + " " + name.getName();
   }
}
