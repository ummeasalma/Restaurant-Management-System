package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class NewEmployeeController {
	
	@FXML private Label lblNewEmployeeStatus;
	@FXML private TextField txtUsername;
	@FXML private TextField txtFirstname;
	@FXML private TextField txtLastname;
	@FXML private TextField txtType;
	
	private ObservableList<Employee> list = FXCollections.observableArrayList();
	@FXML
    void initialize() {

	}
	private ObservableList<Employee> getData(){
    	list.clear();
    	String query = "select * from employee";

    	try {
    		PreparedStatement preparedStatement = DBHandler.connect().prepareStatement(query);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while (resultSet.next()) {
    			list.add(new Employee(resultSet.getString(1),
    					resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
    
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
    	return list;
    }
	@FXML
	public void addEmployee(ActionEvent event) throws Exception {
		
		ManageEmployeeController employee =new ManageEmployeeController();
		Employee in = new Employee(txtFirstname.getText(), txtLastname.getText(),txtUsername.getText(),txtType.getText());
		
    	//ManageEmployeeController controller = loader.getController();
		
		try {
			employee.add(in);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		((Node) event.getSource()).getScene().getWindow().hide();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ManageEmployees.fxml"));
		Parent root = (Parent) loader.load();
		Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
		
	}
	
	
	@FXML
    void cancelEmployee(ActionEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

    }
		
		
	
	}
