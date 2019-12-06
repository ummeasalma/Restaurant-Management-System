package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {
	public static Connection connect() {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:iit.db");
			return connection;
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
			
		}
	
		return null;

	}

}
