package database;

import java.sql.SQLException;



import domain.model.School;

public interface SchoolPersistence 
{
	
	School loadSchool() throws SQLException;
	
	int saveSchool(School school) throws SQLException;
	
	
}
