package domain.model;
import java.io.Serializable;
import java.util.HashMap;

public class CPRFactory implements Serializable
{
   private static final HashMap cprMap = new HashMap();
   private static final long serialVersionUID = 1L;

   
   
   public static ICPR getCPR(String cpr) {
      CPR cprClass = (CPR)cprMap.get(cpr);
      
      if(cprClass == null) {
         cprClass = new CPR(cpr);
         cprMap.put(cpr, cprClass);
      }
      return cprClass;
   }
}
