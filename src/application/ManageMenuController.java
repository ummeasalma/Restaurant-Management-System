package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManageMenuController{

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;
    
    @FXML
    private TableView<ItemObject> tvItems;
    
    @FXML
    private TableColumn<ItemObject, Integer> id;

    @FXML
    private TableColumn<ItemObject, String> itemName;

    @FXML
    private TableColumn<ItemObject, Integer> itemPrice;
    
    
	private ObservableList<ItemObject> list = FXCollections.observableArrayList();
	
	 @FXML
    void initialize() {
		 
		 btnDelete.setStyle("-fx-background-color:  #c0392b ; ");
		 
		// btnAdd.setOnAction(event->{
			 
		 //});
//		manager = new LoginManager();
		// TODO Auto-generated method stub
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		itemName.setCellValueFactory(new PropertyValueFactory<>("item"));
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		list = getData();
		tvItems.setItems(list);
		
		
	}
	 
	 @FXML
    public void home(ActionEvent event) {

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
    
    private ObservableList<ItemObject> getData(){
    	list.clear();
    	String query = "select * from add_menu";

    	try {
    		PreparedStatement preparedStatement = DBHandler.connect().prepareStatement(query);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while (resultSet.next()) {
    			list.add(new ItemObject(resultSet.getInt(1),
    					resultSet.getString(2), resultSet.getInt(3)));
    
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} 
    	return list;
    }
    
    
  //To add item in menu
    
public void add(ActionEvent event){		
		
		LoginManager manager=new LoginManager();
		ItemObject in = new ItemObject(txtItem.getText(), Integer.valueOf(txtPrice.getText()));
		try {
			manager.add(in);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		tvItems.setItems(getData());
		
	}
    
    //To delete item from menu
    @FXML
    void delete(ActionEvent event) {
    	
    	
    	LoginManager manager=new LoginManager();
    	//System.out.println("haha");
    	ItemObject itemObject = tvItems.getSelectionModel().getSelectedItem();
    	System.out.println(itemObject.getId());
		try {
			manager.delete(itemObject.getId());
			System.out.println("Success");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tvItems.setItems(getData());

    }
    
    
}
