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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageEmployeeController {

    @FXML
    private TableView<Employee> tvEmployeeTable;

    @FXML
    private TableColumn<Employee, Integer> id;

    @FXML
    private TableColumn<Employee, String> firstname;

    @FXML
    private TableColumn<Employee, String> lastname;

    @FXML
    private TableColumn<Employee, String> username;

    @FXML
    private TableColumn<Employee, String> employeeType;

    @FXML
    private TextArea log;
    
    
    EmployeeManager manager;
	private ObservableList<Employee> list = FXCollections.observableArrayList();
	
	@FXML
    void initialize(){
		manager = new EmployeeManager();
		
		
		id.setCellValueFactory(new PropertyValueFactory<>("employee_no"));
		firstname.setCellValueFactory(new PropertyValueFactory<>("first_name"));
		lastname.setCellValueFactory(new PropertyValueFactory<>("last_name"));
		username.setCellValueFactory(new PropertyValueFactory<>("username"));
		employeeType.setCellValueFactory(new PropertyValueFactory<>("type"));
	
	
		tvEmployeeTable.setItems(getData());
	}

    @FXML
    void Home(ActionEvent event) {
    	try {
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage primaryStage = new Stage();
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/application/Homepage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void deleteEmployee(ActionEvent event) {
    	EmployeeManager manager = new EmployeeManager();
    	
    	Employee employee = tvEmployeeTable.getSelectionModel().getSelectedItem();
    	System.out.println(employee.getEmployee_no());
		try {
			manager.deleteEmployee(employee.getEmployee_no());
			System.out.println("Success");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tvEmployeeTable.setItems(getData());


    }

    @FXML
    void goToNewEmployeePage(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewEmployee.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

    }
    
    public void add(Employee in) throws SQLException {		
    	PreparedStatement preparedStatement = null;
    	String query = "INSERT INTO employee (first_name, last_name, username, type) VALUES(?,?,?,?)";	
    	try {
    		preparedStatement =DBHandler.connect().prepareStatement(query);
    	
    		//preparedStatement.setInt(1,in.getEmployee_no());
    		preparedStatement.setString(1,in.getFirst_name());
    		preparedStatement.setString(2,in.getLast_name());
    		preparedStatement.setString(3,in.getUsername());
    		preparedStatement.setString(4,in.getType());
    		
    		preparedStatement.executeUpdate();
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		preparedStatement.close();
    		//resultSet.close();
    	}
    }


    @FXML
    void showLog(ActionEvent event) throws IOException {
    	
    	//Employee employee = tvEmployeeTable.getSelectionModel().getSelectedItem();
    	
    	String data = "";
    	for(Employee employee_no : list){
    		data += employee_no.getEmployee_no() + " - " + employee_no.getFirst_name() +  " - " + employee_no.getLast_name() + "\n";
    		
    	log.setText(data);
    	
    	}
    	
    	

    }
    
	public ObservableList<Employee> getData() {
		System.out.println("haha");
		ObservableList<Employee> employee = FXCollections.observableArrayList();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from employee";

		try {
			preparedStatement = DBHandler.connect().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.add(new Employee(resultSet.getInt(1),
						resultSet.getString(2), resultSet.getString(3),resultSet.getString(4), resultSet.getString(5)));
				System.out.println(resultSet.getString(2));
			}
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
