package application;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class HomepageController {

    //@FXML
    //private AnchorPane allButtons;

    @FXML
    private Button menuManager;

    @FXML
    private Button staffManager;

    @FXML
    private Button btnOrders;
    
    @FXML
    private TextArea history;

    @FXML
    private Button btnManage;

    @FXML
    private Label lblPlatformTotal;

    
    public void setHistory(String text){
    	history.setText(text);
    }
    
 	
 	@FXML
    void initialize() {
 		//System.out.println("initialize");
 		//lblPlatformTotal.setText("BDT" + NewOrderController.getTotal() + ".00");
 		history.setText("No items");
 		}
    
   
    
    public void setOrderItems(ObservableList<ItemData> myItems){
        		history.appendText("zxc");
    }
    

    @FXML
    public void newOrder(ActionEvent event) throws IOException {
    	
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewMenuItem.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
    
    @FXML
    void goToEmployeeManager(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/ManageEmployees.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();


    }
    
    @FXML
    void goToOrder(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewOrder.fxml"));
		Scene scene = new Scene(root, 900, 500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    
    @FXML
    void modifyOrder(ActionEvent event) {
    	
    	
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	((Node) event.getSource()).getScene().getWindow().hide();
    	Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginForm.fxml"));
		Scene scene = new Scene(root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();


    }
}
