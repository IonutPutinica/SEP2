package database;

import java.sql.SQLException;


import java.util.ArrayList;

import domain.model.*;

public class DatabaseAdapter implements SchoolPersistence {
	private Database db;
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";

	public DatabaseAdapter() throws ClassNotFoundException {
		this.db = new Database(DRIVER, URL, USER, PASSWORD);
	}

	@Override
	public School loadSchool() throws SQLException {
		School school = new School();

		ArrayList<Object[]> list = this.db.query("SELECT * FROM  \"Teacher\";");

		TeacherList teacherList = new TeacherList();

		for (int i = 0; i < list.size(); i++) {
			Teacher teacher = new Teacher((String) list.get(i)[0], (String) list.get(i)[1]);
			teacherList.addTeacher(teacher);
		}

		school.setTeacherList(teacherList);

		ArrayList<Object[]> courseList = this.db.query("SELECT * FROM \"Course\";");
		ArrayList<Object[]> studentList = this.db.query("SELECT * FROM \"Student\";");
		ArrayList<Object[]> subjectList = this.db.query("SELECT * FROM \"Subject\";");

		for (int i = 0; i < courseList.size(); i++) {
			Teacher teacher = new Teacher("not", "good");
			Course course = new Course("wow", teacher);
			for (int x = 0; x < teacherList.size(); x++) {
				if (teacherList.getTeacher(x).getCPR().equals((String) courseList.get(i)[1])) {
					course = new Course((String) courseList.get(i)[0], teacherList.getTeacher(x));
					for (int n = 0; n < studentList.size(); n++) {
						if (studentList.get(n)[0].equals(course.getName())) {
							Parent parent = new Parent((String) studentList.get(n)[4], (String) studentList.get(n)[3]);
							ProxyStudent student = new ProxyStudent((String) studentList.get(n)[1],
									(String) studentList.get(n)[2], parent);
							student.setCourse(course);
							for(int m = 0; m < subjectList.size(); m++) {
								if (subjectList.get(m)[0].equals("Math")) {
									if(subjectList.get(m)[3].equals(student.getCPR())) {
										Subject subject = new Subject("Math");
										subject.setGrades((String)subjectList.get(m)[2]);
										subject.setAttendance((String)subjectList.get(m)[1]);
										student.setMath(subject);
									}
								}
								if(subjectList.get(m)[0].equals("Literature")) {
									if (subjectList.get(m)[3].equals(student.getCPR())) {
										Subject subject = new Subject("Literature");
										subject.setGrades((String)subjectList.get(m)[2]);
										subject.setAttendance((String)subjectList.get(m)[1]);
										student.setMath(subject);
									}
								}
							}
							course.addStudent(student);
						}
					}
					school.addCourse(course);
				} else {
					System.out.println(
							"System did not find TeacherCPR and CourseCPR match. PLEASE restart the system and fix the issue");
				}
			}
		}
		return school;
	}

	@Override
	public int saveSchool(School school) throws SQLException 
	{	
		int update = 0;
		
		this.db.update("DELETE FROM \"Course\";");
		update++;
		for(int i = 0; i < school.getCourseListSize(); i++) 
		{
			this.db.update("INSERT INTO \"Course\" (CourseID, TeacherCPR) VALUES (?, ?);"
				, school.getCourse(i).getName()
				, school.getCourse(i).getTeacher());
			update +=2;
		}
			
		this.db.update("DELETE FROM \"Teacher\";");
		update++;
		for(int x = 0; x < school.getTeacherList().size(); x++) 
		{
			this.db.update("INSERT INTO \"Teacher\" (Name, CPR) VALUES (?, ?);"
					, school.getTeacherList().getTeacher(x).getName()
					, school.getTeacherList().getTeacher(x).getCPR());
			update += 2;
		}
				
		this.db.update("DELETE FROM \"Student\";");
		update++;
		for(int n = 0; n < school.getCourseListSize(); n++) 
		{
			for(int k = 0; k < school.getCourse(n).getStudentListSize(); k++) {
			this.db.update("INSERT INTO \"Student\" (ClassID, Name, CPR, ParentCPR, ParentName) "
					+ "VALUES (?, ?, ?, ?, ?);"
					, school.getCourse(n).getName()
					, school.getCourse(n).getStudent(k).getName()
					, school.getCourse(n).getStudent(k).getCPR()
					, school.getCourse(n).getStudent(k).getParent().getCPR()
					, school.getCourse(n).getStudent(k).getParent().getName());
			update += 5;
			}
		}
			
		this.db.update("DELETE FROM \"Subject\";");
		update++;
		for(int y = 0; y < school.getCourseListSize(); y++)
		{
			for(int g = 0; g < school.getCourse(y).getStudentListSize(); g++) {
				String math = new String("Math");
			this.db.update("INSERT INTO \"Subject\" (SubjectType, StudentCPR, Grade, Attendance) "
					+ "VALUES (?, ?, ?, ?);"
					, math
					, school.getCourse(y).getStudent(g).getCPR()
					, school.getCourse(y).getStudent(g).getMath().getGrades()
					, school.getCourse(y).getStudent(g).getMath().getAttendance());
			
			String literature = new String("Literature");
			this.db.update("INSERT INTO Subject (SubjectType, StudentCPR, Grade, Attendance) "
					+ "VALUES (?, ?, ?, ?);"
					, literature
					, school.getCourse(y).getStudent(g).getCPR()
					, school.getCourse(y).getStudent(g).getLiterature().getGrades()
					, school.getCourse(y).getStudent(g).getLiterature().getAttendance());
			update += 8;
			}
		}
							
		return update;

	}
}
