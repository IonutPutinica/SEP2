package domain.model;
import java.io.Serializable;
import java.util.ArrayList;


public class TeacherList implements Serializable
{
   private ArrayList<Teacher> teacherList;
   private static TeacherList instance;
   private static final long serialVersionUID = 1L;

   
   public TeacherList() {
      teacherList = new ArrayList<Teacher>();
   }
   
   public void addTeacher(Teacher teacher) {
      teacherList.add(teacher);
   }
   
   public Teacher getTeacher(int index) {
      return teacherList.get(index);
   }
   
   public void removeTeacher(int index) {
      teacherList.remove(index);
   }
   
   public String[] listTeachers() {
      String[] teachers = new String[teacherList.size()];
      for(int i = 0; i < teacherList.size(); i++) {
         teachers[i] = teacherList.get(i).toString();
      }
      return teachers;
   }
   
   public static TeacherList getInstance() {
      if(instance == null)
         instance = new TeacherList();
      return instance;
   }
   
   public int size() {
      return teacherList.size();
   }
}
