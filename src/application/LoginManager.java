package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginManager {
	Connection connection;
	
	
	public LoginManager(){
		
		connection= DBHandler.connect();

	}
	
public boolean login(String username, String pass) throws SQLException{
	boolean status = false;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String query = "select * from user where username= ? and password = ?";
	
	try{
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, pass);
		resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next()){
			status = true;
		}
		
			
	}catch (SQLException e){
		e.printStackTrace();
	}finally{
		preparedStatement.close();
		resultSet.close();
	}
	
	return status;
}
//for menu part
public ObservableList<ItemObject> getData() throws SQLException {
	System.out.println("haha");
	ObservableList<ItemObject> add_menu = FXCollections.observableArrayList();
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	String query = "select * from add_menu2";

	try {
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			add_menu.add(new ItemObject(resultSet.getInt(1),
					resultSet.getString(2), resultSet.getInt(3)));
			System.out.println(resultSet.getString(2));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		preparedStatement.close();
		resultSet.close();
	}
	return add_menu;
}


//add item to menu
public void add(ItemObject in) throws SQLException {		
	PreparedStatement preparedStatement = null;
	String query = "INSERT INTO add_menu (item, price) VALUES(?,?)";	
	try {
		preparedStatement = connection.prepareStatement(query);
	
		preparedStatement.setString(1,in.getItem());
		preparedStatement.setInt(2,in.getPrice());
		
		preparedStatement.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		preparedStatement.close();
		//resultSet.close();
	}
	}




public void delete(int id) throws SQLException {
	String query = "delete from add_menu where id = ?";

	try {
		PreparedStatement preparedStatement = DBHandler.connect().prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} 
	
}

}
