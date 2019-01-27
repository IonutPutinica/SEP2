package domain.model;
import java.io.Serializable;
import java.util.HashMap;
public class NameFactory implements Serializable
{
   private static final HashMap nameMap = new HashMap();
   private static final long serialVersionUID = 1L;

   
   
   public static IName getName(String name) {
      Name nameClass = (Name)nameMap.get(name);
      
      if(nameClass == null) {
         nameClass = new Name(name);
         nameMap.put(name, nameClass);
      }
      return nameClass;
   }
}
