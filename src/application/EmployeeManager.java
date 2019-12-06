package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeManager {
	Connection connection;
	
	
	public EmployeeManager(){
		connection= DBHandler.connect();
	}
	
	public ObservableList<Employee> getData() {
		ObservableList<Employee> employee = FXCollections.observableArrayList();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from employee";

		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.add(new Employee(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(5)));
				//System.out.println(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
	
	public void deleteEmployee(int employee_no) throws SQLException {
		String query = "delete from employee where employee_no = ?";

		try {
			PreparedStatement preparedStatement = DBHandler.connect().prepareStatement(query);
			preparedStatement.setInt(1, employee_no);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}

}