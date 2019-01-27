package database;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Database 
{

	private String url;
	private String user;
	private String pw;
	private Connection connection;

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "admin";

	public Database(String driver, String url, String user, String pw) throws ClassNotFoundException 
	{
		this.url = url;
		this.user = user;
		this.pw = pw;
		connection = null;
		Class.forName(driver);
	}

	public Database(String databaseName, String user, String pw) throws ClassNotFoundException 
	{
		this(DRIVER, URL + databaseName, user, pw);
	}

	public Database(String databaseName) throws ClassNotFoundException 
	{
		this(DRIVER, URL + "?currentSchema=" + databaseName, USER, PASSWORD);
	}

	public Database() throws ClassNotFoundException 
	{
		this(DRIVER, URL, USER, PASSWORD);
	}

	private void openDatabase() throws SQLException 
	{
		connection = DriverManager.getConnection(url, user, pw);
	}

	private void closeDatabase() throws SQLException 
	{
		connection.close();
	}

	public ArrayList<Object[]> query(String sql, Object... statementElements) throws SQLException 
	{
		openDatabase();

		PreparedStatement statement = null;
		ArrayList<Object[]> list = null;
		ResultSet resultSet = null;
		if (sql != null && statement == null) 
		{
			statement = connection.prepareStatement(sql);
			if (statementElements != null) 
			{
				for (int i = 0; i < statementElements.length; i++)
					statement.setObject(i + 1, statementElements[i]);
			}
		}
		resultSet = statement.executeQuery();
		list = new ArrayList<Object[]>();
		while (resultSet.next()) 
		{
			Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
			for (int i = 0; i < row.length; i++) 
			{
				row[i] = resultSet.getObject(i + 1);
			}
			list.add(row);
		}
		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
		closeDatabase();
		return list;
	}

	public int update(String sql, Object... statementElements) throws SQLException 
	{
		openDatabase();
		PreparedStatement statement = connection.prepareStatement(sql);
		if (statementElements != null) 
		{
			for (int i = 0; i < statementElements.length; i++)
				statement.setObject(i + 1, statementElements[i]);
		}

		int result = statement.executeUpdate();

		closeDatabase();
		return result;
	}
	
	public int[] updateAll(ArrayList<String> sqlList) throws SQLException 
	{
		if (sqlList == null)
			return null;

		openDatabase();
		int[] results = new int[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) 
		{
			PreparedStatement statement = connection.prepareStatement(sqlList.get(i));
			results[i] = statement.executeUpdate();
		}
		closeDatabase();
		return results;
	}
	
	public int[] updateAll(String fileName) throws SQLException, FileNotFoundException 
	{
		ArrayList<String> sqlList = readFile(fileName, ";");
		return updateAll(sqlList);
	}

	private ArrayList<String> readFile(String filename, String deliminator) throws FileNotFoundException 
	{
		Scanner input = new Scanner(new FileInputStream(filename));
		ArrayList<String> list = new ArrayList<String>();
		String sql = "";
		while (input.hasNext()) 
		{
			sql += input.nextLine();
			if (deliminator == null || sql.trim().endsWith(deliminator)) 
			{
				list.add(sql);
				sql = "";
			} else if (sql.length() > 0) 
			{
				sql += "\n";
			}
		}
		input.close();
		return list;
	}
	

}
